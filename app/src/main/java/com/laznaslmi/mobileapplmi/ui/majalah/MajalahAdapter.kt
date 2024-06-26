package com.laznaslmi.mobileapplmi.ui.majalah

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

class MajalahAdapter(private var majalahList: List<MajalahDataClass>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<MajalahAdapter.MajalahViewHolder>(){

    interface OnItemClickListener {
        fun onItemClick(majalah: MajalahDataClass)
    }

    class MajalahViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.img_cover_majalah)
        var title: TextView = itemView.findViewById(R.id.judul_majalah)
        var release: TextView = itemView.findViewById(R.id.tanggal)
        var desc: TextView = itemView.findViewById(R.id.deskripsi_majalah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MajalahViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_majalah, parent, false)
        return MajalahViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return majalahList.size
    }

    override fun onBindViewHolder(holder: MajalahViewHolder, position: Int) {
        val currentItem = majalahList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholdermajalah)
                    .error(R.drawable.error_image))
            .into(holder.image)
        holder.title.text = currentItem.title
        holder.release.text = currentItem.release.toFormattedDateString()
        holder.desc.text = currentItem.description

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
    }

    fun updateData(newMajalahList: List<MajalahDataClass>){
        majalahList = newMajalahList
        notifyDataSetChanged()
    }

    private fun String.toFormattedDateString(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        return outputFormat.format(date ?: "")
    }
}