package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laznaslmi.mobileapplmi.R

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
            .into(holder.imgBerita)
        holder.titleBerita.text = currentItem.title
        holder.dateBerita.text = currentItem.date
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
    }

    fun updateData(newBeritaList: List<BeritaDataClass>) {
        beritaList = newBeritaList
        notifyDataSetChanged()
    }
}
