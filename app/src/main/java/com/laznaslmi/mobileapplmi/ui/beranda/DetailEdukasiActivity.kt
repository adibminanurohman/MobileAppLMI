package com.laznaslmi.mobileapplmi.ui.beranda

import android.content.Intent
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
import java.text.SimpleDateFormat
import java.util.Locale

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

        binding.icBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.icShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "https://drive.google.com/drive/folders/14kAtUjC64PnoyQLHqHOismjXD2ZKOYue?usp=drive_link")
            }
            startActivity(Intent.createChooser(shareIntent, "Share link via"))
        }

        val detailEdukasi = intent.getParcelableExtra<EdukasiDataClass>("edukasi")
        if (detailEdukasi != null) {
            val imageDetailEdukasi: ImageView = binding.imgDetailEdukasi
            val titleDetailEdukasi: TextView = binding.tvTitleDetailEdukasi
            val dateDetailEdukasi: TextView = binding.tvDateDetailEdukasi
            val deskripsiDetailEdukasi: TextView = binding.tvDeskripsiDetailEdukasi

            Glide.with(this).load(detailEdukasi.image).into(imageDetailEdukasi)
            titleDetailEdukasi.text = detailEdukasi.title
            dateDetailEdukasi.text = detailEdukasi.date?.toFormattedDateString()
            deskripsiDetailEdukasi.text = detailEdukasi.body
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun String.toFormattedDateString(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        return outputFormat.format(date ?: "")
    }
}