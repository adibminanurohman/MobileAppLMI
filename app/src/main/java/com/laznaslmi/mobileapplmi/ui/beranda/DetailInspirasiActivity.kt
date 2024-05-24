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
import com.laznaslmi.mobileapplmi.databinding.ActivityDetailInspirasiBinding
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.InspirasiDataClass

class DetailInspirasiActivity : AppCompatActivity() {
    private var _binding: ActivityDetailInspirasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDetailInspirasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailInspirasi)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.icBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val detailInspirasi = intent.getParcelableExtra<InspirasiDataClass>("inspirasi")
        if (detailInspirasi != null) {
            val imageDetailInspirasi: ImageView = binding.imgDetailInspirasi
            val titleDetailInspirasi: TextView = binding.tvTitleDetailInspirasi
            val dateDetailInspirasi: TextView = binding.tvDateDetailInspirasi
            val deskripsiDetailInspirasi: TextView = binding.tvDeskripsiDetailInspirasi

            Glide.with(this).load(detailInspirasi.image).into(imageDetailInspirasi)
            titleDetailInspirasi.text = detailInspirasi.title
            dateDetailInspirasi.text = detailInspirasi.date
            deskripsiDetailInspirasi.text = detailInspirasi.body
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}