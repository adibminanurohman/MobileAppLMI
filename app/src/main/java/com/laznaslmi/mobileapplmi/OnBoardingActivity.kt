package com.laznaslmi.mobileapplmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
    }

    fun navigateToBeranda(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}