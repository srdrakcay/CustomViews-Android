package com.serdar.customviews_android.path

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import com.serdar.customviews_android.R

class CustomPathView : View {
    private var paint: Paint=Paint()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)


    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val path = Path()

        path.moveTo(100f, 100f)
        path.quadTo(500f, 100f, 400f, 700f)
        path.close()


        canvas?.drawPath(path, paint)
    }




}
//Kotlin veya Java gibi dillerde, özel bir görünüm (custom view) oluştururken `Path` sınıfı, çeşitli grafik nesnelerini çizmek veya yolu tanımlamak için kullanabileceğiniz bir önemli araçtır. İşte `Path` sınıfının kullanım alanlarından bazıları:
//
//1. **Çizim Yollarını Tanımlama**: `Path` nesnesi, bir çizgi veya yolun tanımlanması için kullanılır. Bir çizgi, çokgen, daire, eğri veya daha karmaşık bir şekil gibi grafik nesnelerini tanımlamak için kullanılabilir.
//
//2. **Özel Şekiller Oluşturma**: Özel şekiller oluşturmak ve bunları çizmek için kullanabilirsiniz. `Path` nesnesi, bu şekilleri tanımlamak için gerekli komutları ve çizim yollarını içerir.
//
//3. **Çizgi ve Eğri Çizimi**: `Path` sınıfı, doğru çizimleri, eğrileri ve çokgenleri tanımlamak ve çizmek için kullanılır. Özellikle serbestçe çizilen eğrileri tanımlamak için kullanışlıdır.
//
//4. **Doldurma ve Kenar Çizimi**: `Path` nesnesini kullanarak içini doldurabileceğiniz veya kenar çizimini (stroke) yapabileceğiniz şekiller oluşturabilirsiniz.
//
//5. **Metin Yolu Çizimi**: Metin yolu çizimi için de kullanılabilir. Örneğin, metni belirli bir yolda takip eden özel metin düzenlemeleri oluşturmak için `Path` kullanılabilir.
//
//6. **Grafik Efektleri**: Görsel efektler veya animasyonlar oluşturmak için `Path` kullanabilirsiniz. Bir `Path` nesnesini kullanarak animasyonlu bir yol çizebilirsiniz.

//`Path` sınıfı, özel grafik çizimleri oluşturmanın ve işlemenin temel bir aracıdır ve özel görünümler veya çizimler oluştururken sıkça kullanılır.