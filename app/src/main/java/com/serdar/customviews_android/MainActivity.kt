package com.serdar.customviews_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.serdar.customviews_android.databinding.ActivityMainBinding
import com.serdar.customviews_android.learning.selectedpoint.pages.ViewPagerAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter:ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter=ViewPagerAdapter(this)
        binding.viewPager.adapter=adapter
        setPagePosition()
    }
    private fun setPagePosition(){
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.customView.setPosition(position)
            }
        })
    }


}