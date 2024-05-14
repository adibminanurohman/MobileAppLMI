package com.laznaslmi.mobileapplmi.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.laznaslmi.mobileapplmi.R

class ThirdScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Memuat tata letak untuk fragmen ini
        val view = inflater.inflate(R.layout.fragment_third_screen, container, false)

        // Mendapatkan referensi tombol "Mulai Donasi Sekarang" dari tata letak
        val mulaiDonasiSekarang = view.findViewById<Button>(R.id.btn_mulaidonasi)

        // Menambahkan aksi klik pada tombol "Mulai Donasi Sekarang"
        mulaiDonasiSekarang.setOnClickListener {
            // Navigasi ke homeFragment saat tombol diklik
            findNavController().navigate(R.id.action_onBoarding1Fragment_to_mainActivity)
            // Menandai bahwa onboarding sudah selesai
            onBoardingIsFinished()
        }

        // Mengembalikan tata letak yang dimuat
        return view
    }

    // Fungsi untuk menandai bahwa onboarding sudah selesai
    private fun onBoardingIsFinished() {
        // Mendapatkan shared preferences untuk menyimpan status onboarding
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        // Membuat editor shared preferences
        val editor = sharedPreferences.edit()
        // Menetapkan nilai boolean "finished" menjadi true
        editor.putBoolean("finshed", true)
        // Menyimpan perubahan
        editor.apply()
    }
}
