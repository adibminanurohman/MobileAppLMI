package com.laznaslmi.mobileapplmi.ui.explore.retrofit

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
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image))
            .into(holder.imgSearchExplore)
        holder.titleSearchExplore.text = currentItem.title
        holder.dateSearchExplore.text = currentItem.date?.toFormattedDateString()
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
    }

    fun updateData(newSearchExploreList: List<SearchExploreDataClass>) {
        searchExploreList = newSearchExploreList
        notifyDataSetChanged()
    }

    private fun String.toFormattedDateString(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        return outputFormat.format(date ?: "")
    }
}
