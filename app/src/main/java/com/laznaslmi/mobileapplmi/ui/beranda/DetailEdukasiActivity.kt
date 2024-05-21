package com.laznaslmi.mobileapplmi.ui.beranda

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.laznaslmi.mobileapplmi.R
import com.laznaslmi.mobileapplmi.databinding.ActivityDetailEdukasiBinding
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.EdukasiDataClass

class DetailEdukasiActivity : AppCompatActivity() {
    private var _binding: ActivityDetailEdukasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDetailEdukasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailEdukasi)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val detailEdukasi = intent.getParcelableExtra<EdukasiDataClass>("edukasi")
        if (detailEdukasi != null) {
            val imageDetailEdukasi: ImageView = binding.imgDetailEdukasi
            val titleDetailEdukasi: TextView = binding.tvTitleDetailEdukasi
            val dateDetailEdukasi: TextView = binding.tvDateDetailEdukasi
            val deskripsiDetailEdukasi: TextView = binding.tvDeskripsiDetailEdukasi

            Glide.with(this).load(detailEdukasi.image).into(imageDetailEdukasi)
            titleDetailEdukasi.text = detailEdukasi.title
            dateDetailEdukasi.text = detailEdukasi.date
            deskripsiDetailEdukasi.text = detailEdukasi.body
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}