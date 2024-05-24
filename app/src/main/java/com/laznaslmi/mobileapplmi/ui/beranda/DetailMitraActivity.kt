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
import com.laznaslmi.mobileapplmi.databinding.ActivityDetailMitraBinding
import com.laznaslmi.mobileapplmi.databinding.ActivityDetailProgramBinding
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BeritaDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.MitraDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.ProgramDataClass

class DetailMitraActivity : AppCompatActivity() {
    private var _binding: ActivityDetailMitraBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDetailMitraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailMitra)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.icBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val detailMitra = intent.getParcelableExtra<MitraDataClass>("mitra")
        if (detailMitra != null) {
            val imageDetailMitra: ImageView = binding.imgDetailMitra
            val titleDetailMitra: TextView = binding.tvTitleDetailMitra
            val dateDetailMitra: TextView = binding.tvDateDetailMitra
            val deskripsiDetailMitra: TextView = binding.tvDeskripsiDetailMitra

            Glide.with(this).load(detailMitra.image).into(imageDetailMitra)
            titleDetailMitra.text = detailMitra.title
            dateDetailMitra.text = detailMitra.date
            deskripsiDetailMitra.text = detailMitra.body
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}