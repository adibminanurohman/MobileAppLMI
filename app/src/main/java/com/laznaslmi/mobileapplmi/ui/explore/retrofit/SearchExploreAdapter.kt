package com.laznaslmi.mobileapplmi.ui.explore.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laznaslmi.mobileapplmi.R

class SearchExploreAdapter(private var searchExploreList: List<SearchExploreDataClass>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<SearchExploreAdapter.SearchExploreViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(banners: SearchExploreDataClass)
    }

    class SearchExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgSearchExplore: ImageView = itemView.findViewById(R.id.img_imgSearchExplore)
        var titleSearchExplore: TextView = itemView.findViewById(R.id.tv_titleSearchExplore)
        var dateSearchExplore: TextView = itemView.findViewById(R.id.tv_dateSearchExplore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchExploreViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_search_explore, parent, false)
        return SearchExploreViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return searchExploreList.size
    }

    override fun onBindViewHolder(holder: SearchExploreViewHolder, position: Int) {
        val currentItem = searchExploreList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.imgSearchExplore)
        holder.titleSearchExplore.text = currentItem.title
        holder.dateSearchExplore.text = currentItem.date
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
    }

    fun updateData(newSearchExploreList: List<SearchExploreDataClass>) {
        searchExploreList = newSearchExploreList
        notifyDataSetChanged()
    }
}
