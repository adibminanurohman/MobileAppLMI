package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

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
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image))
            .into(holder.imgBerita)
        holder.titleBerita.text = currentItem.title
        holder.dateBerita.text = currentItem.date?.toFormattedDateString()
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
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
