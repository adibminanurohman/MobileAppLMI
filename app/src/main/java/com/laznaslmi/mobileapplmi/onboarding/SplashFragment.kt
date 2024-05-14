package com.laznaslmi.mobileapplmi.onboarding

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBar
import com.laznaslmi.mobileapplmi.R

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        // Menyembunyikan action bar
        hideActionBar()

        // Handler untuk menunda navigasi ke fragmen selanjutnya (onboardingFragment)
        Handler(Looper.getMainLooper()).postDelayed({

            // Menentukan apakah onboarding sudah selesai atau belum
            if (onBoardingIsFinished()){
                // Jika onboarding sudah selesai, navigasikan ke homeFragment
                findNavController().navigate(R.id.action_splashFragment_to_mainActivity)
            } else {
                // Jika onboarding belum selesai, navigasikan ke onBoarding1Fragment
                findNavController().navigate(R.id.action_splashFragment_to_onBoarding1Fragment)
            }
        }, 3000)

        // Mengembalikan tata letak yang dimuat
        return view
    }

    // Fungsi untuk mengecek apakah onboarding sudah selesai atau belum
    private fun onBoardingIsFinished(): Boolean{
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        // Mengembalikan nilai boolean yang menunjukkan apakah onboarding sudah selesai atau belum
        return sharedPreferences.getBoolean("finshed", false)
    }

    // Fungsi untuk menyembunyikan action bar
    private fun hideActionBar() {
        val actionBar: ActionBar? = (activity as? AppCompatActivity)?.supportActionBar
        actionBar?.hide()
    }
}
