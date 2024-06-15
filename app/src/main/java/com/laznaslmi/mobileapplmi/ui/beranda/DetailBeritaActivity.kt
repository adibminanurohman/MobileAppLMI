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
import com.laznaslmi.mobileapplmi.databinding.ActivityDetailBeritaBinding
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BeritaDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.PostDataClass
import java.text.SimpleDateFormat
import java.util.Locale

class DetailBeritaActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBeritaBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDetailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailBerita)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.icBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val detailBerita = intent.getParcelableExtra<BeritaDataClass>("berita")
        if (detailBerita != null) {
            val imageDetailBerita: ImageView = binding.imgDetailBerita
            val titleDetailBerita: TextView = binding.tvTitleDetailBerita
            val dateDetailBerita: TextView = binding.tvDateDetailBerita
            val deskripsiDetailBerita: TextView = binding.tvDeskripsiDetailBerita

            Glide.with(this).load(detailBerita.image).into(imageDetailBerita)
            titleDetailBerita.text = detailBerita.title
            dateDetailBerita.text = detailBerita.date?.toFormattedDateString()
            deskripsiDetailBerita.text = detailBerita.body
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