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

class InspirasiAdapter(private var inspirasiList: List<InspirasiDataClass>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<InspirasiAdapter.InspirasiViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(banners: InspirasiDataClass)
    }

    class InspirasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgInspirasi: ImageView = itemView.findViewById(R.id.img_imgInspirasi)
        var titleInspirasi: TextView = itemView.findViewById(R.id.tv_titleInspirasi)
        var dateInspirasi: TextView = itemView.findViewById(R.id.tv_dateInspirasi)
        var btnShareInspirasi: ImageView = itemView.findViewById(R.id.btnShareInspirasi)

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
                    .placeholder(R.drawable.placeholderpost)
                    .error(R.drawable.placeholderposteror))
            .into(holder.imgInspirasi)
        holder.titleInspirasi.text = currentItem.title
        holder.dateInspirasi.text = currentItem.date?.toFormattedDateString()
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
        holder.btnShareInspirasi.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://drive.google.com/drive/folders/14kAtUjC64PnoyQLHqHOismjXD2ZKOYue?usp=drive_link")
            holder.itemView.context.startActivity(Intent.createChooser(shareIntent, "Share link via"))
        }
    }

    fun updateData(newInspirasiList: List<InspirasiDataClass>) {
        inspirasiList = newInspirasiList
        notifyDataSetChanged()
    }

    private fun String.toFormattedDateString(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        return outputFormat.format(date ?: "")
    }
}
