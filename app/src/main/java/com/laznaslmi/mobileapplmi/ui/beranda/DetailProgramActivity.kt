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
import com.laznaslmi.mobileapplmi.databinding.ActivityDetailProgramBinding
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BeritaDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.ProgramDataClass
import java.text.SimpleDateFormat
import java.util.Locale

class DetailProgramActivity : AppCompatActivity() {
    private var _binding: ActivityDetailProgramBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDetailProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailPrgram)) { v, insets ->
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

        val detailProgram = intent.getParcelableExtra<ProgramDataClass>("program")
        if (detailProgram != null) {
            val imageDetailProgram: ImageView = binding.imgDetailProgram
            val titleDetailProgram: TextView = binding.tvTitleDetailProgram
            val dateDetailProgram: TextView = binding.tvDateDetailProgram
            val deskripsiDetailProgram: TextView = binding.tvDeskripsiDetailProgram

            Glide.with(this).load(detailProgram.image).into(imageDetailProgram)
            titleDetailProgram.text = detailProgram.title
            dateDetailProgram.text = detailProgram.date?.toFormattedDateString()
            deskripsiDetailProgram.text = detailProgram.body
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