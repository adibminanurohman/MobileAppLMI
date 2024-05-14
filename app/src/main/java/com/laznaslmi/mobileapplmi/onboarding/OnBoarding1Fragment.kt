package com.laznaslmi.mobileapplmi.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.laznaslmi.mobileapplmi.R
import com.laznaslmi.mobileapplmi.adapter.ViewPagerAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnBoarding1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Memuat tata letak untuk fragmen ini
        val view =  inflater.inflate(R.layout.fragment_on_boarding1, container, false)

        // Daftar fragmen untuk ditampilkan dalam ViewPager2
        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        // Membuat adapter untuk ViewPager2
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        // Mendapatkan referensi ke ViewPager2 dari tata letak
        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)

        // Mengatur adapter ke ViewPager2
        viewPager.adapter = adapter

        // Mendapatkan referensi ke indikator titik dari tata letak
        val indicator = view.findViewById<DotsIndicator>(R.id.dots_indicator)

        // Menyambungkan indikator titik ke ViewPager2
        indicator.attachTo(viewPager)

        // Mengembalikan tata letak yang dimuat
        return view
    }
}
