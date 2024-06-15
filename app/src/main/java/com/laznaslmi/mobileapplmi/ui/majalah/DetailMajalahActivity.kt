package com.laznaslmi.mobileapplmi.ui.majalah

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.laznaslmi.mobileapplmi.R
import com.laznaslmi.mobileapplmi.databinding.ActivityDetailMajalahBinding
import com.laznaslmi.mobileapplmi.databinding.FragmentMajalahBinding
import com.laznaslmi.mobileapplmi.ui.majalah.MajalahDataClass
import java.text.SimpleDateFormat
import java.util.Locale

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

        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
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
            tanggal.text = detailMajalah.release.toFormattedDateString()
            views.text = detailMajalah.views.toString()
            deskripsi.text = detailMajalah.description

            binding.bacaOnline.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(detailMajalah.link))
                startActivity(intent)
            }
            binding.unduh.setOnClickListener {
                val request = DownloadManager.Request(Uri.parse(detailMajalah.link))
                    .setTitle("Download Majalah")
                    .setDescription("Download file from ${detailMajalah.link}")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${detailMajalah.title}.pdf")
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true)

                val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                downloadManager.enqueue(request)
                Toast.makeText(this, "Download Started", Toast.LENGTH_SHORT).show()
            }
            binding.kirim.setOnClickListener {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Majalah ini sangat menginspirasi ${detailMajalah.link}")
                    type = "text/plain"
                }
                val chooser = Intent.createChooser(shareIntent, "Share via")
                if (shareIntent.resolveActivity(packageManager) != null){
                    startActivity(chooser)
                }
                else {
                    Toast.makeText(this, "No app available to share content", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun String.toFormattedDateString(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        return outputFormat.format(date ?: "")
    }
}