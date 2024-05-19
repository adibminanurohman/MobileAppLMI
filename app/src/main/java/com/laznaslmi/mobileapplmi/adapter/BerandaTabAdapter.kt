package com.laznaslmi.mobileapplmi.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.laznaslmi.mobileapplmi.ui.beranda.tab.BeritaFragment
import com.laznaslmi.mobileapplmi.ui.beranda.tab.EdukasiFragment
import com.laznaslmi.mobileapplmi.ui.beranda.tab.InspirasiFragment
import com.laznaslmi.mobileapplmi.ui.beranda.tab.MitraFragment
import com.laznaslmi.mobileapplmi.ui.beranda.tab.ProgramFragment

class BerandaTabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = listOf(
        BeritaFragment(),
        ProgramFragment(),
        InspirasiFragment(),
        EdukasiFragment(),
        MitraFragment()
    )

    private val fragmentTitleList = listOf(
        "Berita",
        "Program",
        "Inspirasi",
        "Edukasi",
        "Mitra"
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList[position]
    }
}
