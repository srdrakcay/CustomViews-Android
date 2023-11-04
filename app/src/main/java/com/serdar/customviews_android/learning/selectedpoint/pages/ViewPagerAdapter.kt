package com.serdar.customviews_android.learning.selectedpoint.pages

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private var position:Int=0
    override fun getItemCount(): Int {
        return 3
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {

            0 -> {
                this.position =0
                FirstFragment()
            }
            1 ->{
                this.position =1
                SecondFragment()
            }
            2 ->{
                this.position =2
                ThirdFragment()
            }
            else -> throw IllegalArgumentException("Geçersiz pozisyon: $position")
        }
    }


}