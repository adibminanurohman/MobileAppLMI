package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.laznaslmi.mobileapplmi.R
import java.text.SimpleDateFormat
import java.util.Locale

class BeritaAdapter(private var beritaList: List<BeritaDataClass>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(banners: BeritaDataClass)
    }

    class BeritaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgBerita: ImageView = itemView.findViewById(R.id.img_imgBerita)
        var titleBerita: TextView = itemView.findViewById(R.id.tv_titleBerita)
        var dateBerita: TextView = itemView.findViewById(R.id.tv_dateBerita)
        var btnShareBerita: ImageView = itemView.findViewById(R.id.btn_shareBerita)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)
        return BeritaViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return beritaList.size
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val currentItem = beritaList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholderpost)
                    .error(R.drawable.placeholderposteror))
            .into(holder.imgBerita)
        holder.titleBerita.text = currentItem.title
        holder.dateBerita.text = currentItem.date?.toFormattedDateString()
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
        holder.btnShareBerita.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://drive.google.com/drive/folders/14kAtUjC64PnoyQLHqHOismjXD2ZKOYue?usp=drive_link")
            holder.itemView.context.startActivity(Intent.createChooser(shareIntent, "Share link via"))
        }
    }

    fun updateData(newBeritaList: List<BeritaDataClass>) {
        beritaList = newBeritaList
        notifyDataSetChanged()
    }

    private fun String.toFormattedDateString(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        return outputFormat.format(date ?: "")
    }
}
