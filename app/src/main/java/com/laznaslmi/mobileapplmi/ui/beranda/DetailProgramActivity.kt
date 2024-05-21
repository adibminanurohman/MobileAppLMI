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
import com.laznaslmi.mobileapplmi.databinding.ActivityDetailProgramBinding
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BeritaDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.ProgramDataClass

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

        val detailProgram = intent.getParcelableExtra<ProgramDataClass>("program")
        if (detailProgram != null) {
            val imageDetailProgram: ImageView = binding.imgDetailProgram
            val titleDetailProgram: TextView = binding.tvTitleDetailProgram
            val dateDetailProgram: TextView = binding.tvDateDetailProgram
            val deskripsiDetailProgram: TextView = binding.tvDeskripsiDetailProgram

            Glide.with(this).load(detailProgram.image).into(imageDetailProgram)
            titleDetailProgram.text = detailProgram.title
            dateDetailProgram.text = detailProgram.date
            deskripsiDetailProgram.text = detailProgram.body
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}