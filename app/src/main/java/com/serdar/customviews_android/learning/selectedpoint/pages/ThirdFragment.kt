package com.serdar.customviews_android.learning.selectedpoint.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serdar.customviews_android.R
import com.serdar.customviews_android.databinding.FragmentFirstBinding
import com.serdar.customviews_android.databinding.FragmentSecondBinding
import com.serdar.customviews_android.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentThirdBinding.inflate(layoutInflater)
        return binding.root
    }


}