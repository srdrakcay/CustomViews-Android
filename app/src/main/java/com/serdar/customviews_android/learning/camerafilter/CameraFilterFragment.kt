package com.serdar.customviews_android.learning.camerafilter

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.serdar.customviews_android.databinding.FragmentCameraFilterBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CameraFilterFragment : Fragment() {
    private lateinit var binding: FragmentCameraFilterBinding
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var backgroundHandler: Handler
    private lateinit var backgroundThread: HandlerThread
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCameraFilterBinding.inflate(layoutInflater)
        backgroundThread = HandlerThread("CameraFilterBackground")
        backgroundThread.start()
        backgroundHandler = Handler(backgroundThread.looper)
        if (allPermissionsGranted()) startCamera() else requestPermissions()

        cameraExecutor = Executors.newSingleThreadExecutor()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        )
        { permissions ->

            var permissionGranted = true

            permissions.entries.forEach {
                if (it.key in REQUIRED_PERMISSIONS && !it.value)
                    permissionGranted = false
            }

            if (!permissionGranted) {
                Toast.makeText(
                    requireContext(),
                    "Permission request denied",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                startCamera()
            }

        }
    @OptIn(ExperimentalGetImage::class) private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({

            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(
                        binding.cameraPreviewView.surfaceProvider
                    )
                }

            // Select front camera as a default
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            val colorMatrix = ColorMatrix()
            colorMatrix.setSaturation(100f) // Adjust the saturation value as needed

// Create a ColorMatrixColorFilter from the ColorMatrix
            val colorFilter = ColorMatrixColorFilter(colorMatrix)
            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {

                    it.setAnalyzer(cameraExecutor) { imageProxy ->
                        // Convert the image to a Bitmap
                        val bitmap = imageProxy.image?.toBitmap()

                        // Apply the color filter to the Bitmap
                        val filteredBitmap = bitmap?.let { it1 -> applyColorFilter(it1, colorFilter) }

                        // Use the filteredBitmap as needed

                        // Close the imageProxy to release its resources
                        imageProxy.close()
                    }
                }



            try {

                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageAnalyzer
                )

            } catch (exception: Exception) {
                Log.e(TAG, "Use case binding failed", exception)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }
    private fun applyColorFilter(bitmap: Bitmap, colorFilter: ColorMatrixColorFilter): Bitmap {
        val mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val paint = Paint()
        paint.colorFilter = colorFilter

        val canvas = Canvas(mutableBitmap)
        canvas.drawBitmap(mutableBitmap, 0f, 0f, paint)

        return mutableBitmap
    }


    private fun requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS)
    }
    fun Image.toBitmap(): Bitmap {
        val buffer = planes[0].buffer
        buffer.rewind()
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {

        private const val TAG = "ObjectDetectionStudy"

        private val REQUIRED_PERMISSIONS = mutableListOf(
            Manifest.permission.CAMERA
        ).toTypedArray()

    }
}