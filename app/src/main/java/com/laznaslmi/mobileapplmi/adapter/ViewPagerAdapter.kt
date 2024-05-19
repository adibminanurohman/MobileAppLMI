package com.laznaslmi.mobileapplmi.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

// Kelas ViewPagerAdapter digunakan untuk mengatur fragmen dalam ViewPager2.
class ViewPagerAdapter (list: ArrayList<Fragment>, fm : FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fm, lifecycle) {

    // Daftar fragmen yang akan ditampilkan dalam ViewPager2.
    private val fragmentList = list

    // Mengembalikan jumlah total fragmen dalam daftar.
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    // Membuat dan mengembalikan fragmen sesuai dengan posisi yang diberikan.
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}
