package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laznaslmi.mobileapplmi.R

class BannersAdapter(private var bannersList: List<PostDataClass>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<BannersAdapter.BannersViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(banners: PostDataClass)
    }

    class BannersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgBanners: ImageView = itemView.findViewById(R.id.img_banners)
        var titleBanners: TextView = itemView.findViewById(R.id.tv_titlebanners)
        var dateBanners: TextView = itemView.findViewById(R.id.tv_datebanners)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_banners, parent, false)
        return BannersViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return bannersList.size
    }

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        val currentItem = bannersList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.imgBanners)
        holder.titleBanners.text = currentItem.title
        holder.dateBanners.text = currentItem.date
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
    }

    fun updateData(newBannersList: List<PostDataClass>) {
        bannersList = newBannersList
        notifyDataSetChanged()
    }
}
