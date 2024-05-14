package com.laznaslmi.mobileapplmi.ui.majalah

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.laznaslmi.mobileapplmi.R
import com.laznaslmi.mobileapplmi.databinding.ActivityDetailMajalahBinding
import com.laznaslmi.mobileapplmi.databinding.FragmentMajalahBinding
import com.laznaslmi.mobileapplmi.ui.majalah.MajalahDataClass

class DetailMajalahActivity : AppCompatActivity() {
    private var _binding: ActivityDetailMajalahBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDetailMajalahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val detailMajalah = intent.getParcelableExtra<MajalahDataClass>("majalah")
        if (detailMajalah != null){
            val coverMajalah: ImageView = binding.detailCoverMajalah
            val judulMajalah: TextView = binding.detailJudulMajalah
            val tanggal: TextView = binding.detailTanggal
            val views: TextView = binding.detailViews
            val deskripsi: TextView = binding.detailDeskripsi

            Glide.with(this).load(detailMajalah.image).into(coverMajalah)
            judulMajalah.text = detailMajalah.title
            tanggal.text = detailMajalah.release
            views.text = detailMajalah.views.toString()
            deskripsi.text = detailMajalah.description
        }
    }
}