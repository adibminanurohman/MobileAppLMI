package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.laznaslmi.mobileapplmi.R

class BannerAdapter(
    private var banners: List<Banner>,
    private val context: Context
) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bannerImage: ImageView = view.findViewById(R.id.bannerImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banners, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = banners[position]
        Glide.with(context)
            .load(banner.image)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image))
            .into(holder.bannerImage)
    }

    override fun getItemCount(): Int = banners.size.coerceAtMost(5)

    fun updateData(newBanners: List<Banner>) {
        banners = newBanners
        notifyDataSetChanged()
    }
}

