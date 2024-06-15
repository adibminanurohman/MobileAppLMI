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

class MitraAdapter(private var mitraList: List<MitraDataClass>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<MitraAdapter.MitraViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(banners: MitraDataClass)
    }

    class MitraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgMitra: ImageView = itemView.findViewById(R.id.img_imgMitra)
        var titleMitra: TextView = itemView.findViewById(R.id.tv_titleMitra)
        var dateMitra: TextView = itemView.findViewById(R.id.tv_dateMitra)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MitraViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_mitra, parent, false)
        return MitraViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mitraList.size
    }

    override fun onBindViewHolder(holder: MitraViewHolder, position: Int) {
        val currentItem = mitraList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image))
            .into(holder.imgMitra)
        holder.titleMitra.text = currentItem.title
        holder.dateMitra.text = currentItem.date?.toFormattedDateString()
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
    }

    fun updateData(newMitraList: List<MitraDataClass>) {
        mitraList = newMitraList
        notifyDataSetChanged()
    }

    private fun String.toFormattedDateString(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        return outputFormat.format(date ?: "")
    }
}