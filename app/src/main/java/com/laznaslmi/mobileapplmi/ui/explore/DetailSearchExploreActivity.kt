package com.laznaslmi.mobileapplmi.ui.explore

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.laznaslmi.mobileapplmi.R
import com.laznaslmi.mobileapplmi.databinding.ActivityDetailSearchExploreBinding
import com.laznaslmi.mobileapplmi.ui.explore.retrofit.SearchExploreDataClass

class DetailSearchExploreActivity : AppCompatActivity() {
    private var _binding: ActivityDetailSearchExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDetailSearchExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailPrgram)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.icBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val detailSearchExplore = intent.getParcelableExtra<SearchExploreDataClass>("searchExplore")
        if (detailSearchExplore != null) {
            val imageDetailSearchExplore: ImageView = binding.imgDetailSearchExplore
            val titleDetailSearchExplore: TextView = binding.tvTitleDetailSearchExplore
            val dateDetailSearchExplore: TextView = binding.tvDateDetailSearchExplore
            val deskripsiDetailSearchExplore: TextView = binding.tvDeskripsiDetailSearchExplore

            Glide.with(this).load(detailSearchExplore.image).into(imageDetailSearchExplore)
            titleDetailSearchExplore.text = detailSearchExplore.title
            dateDetailSearchExplore.text = detailSearchExplore.date
            deskripsiDetailSearchExplore.text = detailSearchExplore.body
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}