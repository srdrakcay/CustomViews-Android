package com.serdar.customviews_android.learning.selectedpoint.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serdar.customviews_android.R
import com.serdar.customviews_android.databinding.FragmentFirstBinding
import com.serdar.customviews_android.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }


}