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
import java.text.SimpleDateFormat
import java.util.Locale

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
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholderpost)
                    .error(R.drawable.placeholderposteror))
            .into(holder.imgProgram)
        holder.titleProgram.text = currentItem.title
        holder.dateProgram.text = currentItem.date?.toFormattedDateString()
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
    }

    fun updateData(newProgramList: List<ProgramDataClass>) {
        programList = newProgramList
        notifyDataSetChanged()
    }

    private fun String.toFormattedDateString(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        return outputFormat.format(date ?: "")
    }
}
