package com.laznaslmi.mobileapplmi.ui.majalah

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laznaslmi.mobileapplmi.R

class MajalahAdapter(private var majalahList: List<MajalahDataClass>):
    RecyclerView.Adapter<MajalahAdapter.MajalahViewHolder>(){
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
            .into(holder.image)
        holder.title.text = currentItem.title
        holder.release.text = currentItem.release
        holder.desc.text = currentItem.description
    }

    fun updateData(newMajalahList: List<MajalahDataClass>){
        majalahList = newMajalahList
        notifyDataSetChanged()
    }
}