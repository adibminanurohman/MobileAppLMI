package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laznaslmi.mobileapplmi.R

class EdukasiAdapter(private var edukasiList: List<EdukasiDataClass>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<EdukasiAdapter.EdukasiViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(banners: EdukasiDataClass)
    }

    class EdukasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgEdukasi: ImageView = itemView.findViewById(R.id.img_imgEdukasi)
        var titleEdukasi: TextView = itemView.findViewById(R.id.tv_titleEdukasi)
        var dateEdukasi: TextView = itemView.findViewById(R.id.tv_dateEdukasi)
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
            .into(holder.imgEdukasi)
        holder.titleEdukasi.text = currentItem.title
        holder.dateEdukasi.text = currentItem.date
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
    }

    fun updateData(newEdukasiList: List<EdukasiDataClass>) {
        edukasiList = newEdukasiList
        notifyDataSetChanged()
    }
}
