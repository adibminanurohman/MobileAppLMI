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
import com.laznaslmi.mobileapplmi.databinding.ActivityDetailBannersBinding
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.PostDataClass

class DetailBannersActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBannersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDetailBannersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val detailBanners = intent.getParcelableExtra<PostDataClass>("banners")
        if (detailBanners != null) {
            val imageDetailBanners: ImageView = binding.imgDetailBanners
            val titleDetailBanners: TextView = binding.tvTitleDetailBanners
            val dateDetailBanners: TextView = binding.tvDateDetailBanners
            val deskripsiDetailBanners: TextView = binding.tvDeskripsiDetailBanners

            Glide.with(this).load(detailBanners.image).into(imageDetailBanners)
            titleDetailBanners.text = detailBanners.title
            dateDetailBanners.text = detailBanners.date
            deskripsiDetailBanners.text = detailBanners.body
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
