package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import android.content.Intent
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

class EdukasiAdapter(private var edukasiList: List<EdukasiDataClass>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<EdukasiAdapter.EdukasiViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(banners: EdukasiDataClass)
    }

    class EdukasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgEdukasi: ImageView = itemView.findViewById(R.id.img_imgEdukasi)
        var titleEdukasi: TextView = itemView.findViewById(R.id.tv_titleEdukasi)
        var dateEdukasi: TextView = itemView.findViewById(R.id.tv_dateEdukasi)
        var btnShareEdukasi: ImageView = itemView.findViewById(R.id.btnShareEdukasi)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EdukasiViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_edukasi, parent, false)
        return EdukasiViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return edukasiList.size
    }

    override fun onBindViewHolder(holder: EdukasiViewHolder, position: Int) {
        val currentItem = edukasiList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholderpost)
                    .error(R.drawable.placeholderposteror))
            .into(holder.imgEdukasi)
        holder.titleEdukasi.text = currentItem.title
        holder.dateEdukasi.text = currentItem.date?.toFormattedDateString()
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
        holder.btnShareEdukasi.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://drive.google.com/drive/folders/14kAtUjC64PnoyQLHqHOismjXD2ZKOYue?usp=drive_link")
            holder.itemView.context.startActivity(Intent.createChooser(shareIntent, "Share link via"))
        }
    }

    fun updateData(newEdukasiList: List<EdukasiDataClass>) {
        edukasiList = newEdukasiList
        notifyDataSetChanged()
    }

    private fun String.toFormattedDateString(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        return outputFormat.format(date ?: "")
    }
}
