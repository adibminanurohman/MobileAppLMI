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

class InspirasiAdapter(private var inspirasiList: List<InspirasiDataClass>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<InspirasiAdapter.InspirasiViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(banners: InspirasiDataClass)
    }

    class InspirasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgInspirasi: ImageView = itemView.findViewById(R.id.img_imgInspirasi)
        var titleInspirasi: TextView = itemView.findViewById(R.id.tv_titleInspirasi)
        var dateInspirasi: TextView = itemView.findViewById(R.id.tv_dateInspirasi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspirasiViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_inspirasi, parent, false)
        return InspirasiViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return inspirasiList.size
    }

    override fun onBindViewHolder(holder: InspirasiViewHolder, position: Int) {
        val currentItem = inspirasiList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image))
            .into(holder.imgInspirasi)
        holder.titleInspirasi.text = currentItem.title
        holder.dateInspirasi.text = currentItem.date
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
    }

    fun updateData(newInspirasiList: List<InspirasiDataClass>) {
        inspirasiList = newInspirasiList
        notifyDataSetChanged()
    }
}
