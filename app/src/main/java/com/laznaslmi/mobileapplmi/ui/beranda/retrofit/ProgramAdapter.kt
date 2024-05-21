package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laznaslmi.mobileapplmi.R

class ProgramAdapter(private var programList: List<ProgramDataClass>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(banners: ProgramDataClass)
    }

    class ProgramViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgProgram: ImageView = itemView.findViewById(R.id.img_imgProgram)
        var titleProgram: TextView = itemView.findViewById(R.id.tv_titleProgram)
        var dateProgram: TextView = itemView.findViewById(R.id.tv_dateProgram)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_program, parent, false)
        return ProgramViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return programList.size
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        val currentItem = programList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.imgProgram)
        holder.titleProgram.text = currentItem.title
        holder.dateProgram.text = currentItem.date
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
    }

    fun updateData(newProgramList: List<ProgramDataClass>) {
        programList = newProgramList
        notifyDataSetChanged()
    }
}
