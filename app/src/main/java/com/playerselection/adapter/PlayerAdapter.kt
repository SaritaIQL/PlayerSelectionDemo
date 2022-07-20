package com.playerselection.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.playerselection.ApiResponse.player_data
import com.playerselection.R
import com.playerselection.extention.loadImageCustomPlaceHolder
import de.hdodenhof.circleimageview.CircleImageView

class PlayerAdapter(
    val context: Context,
    var arrayList: ArrayList<player_data>,
    val listener: onItemClicklisteners
) : RecyclerView.Adapter<PlayerAdapter.CustomViewHolder>() {
    interface onItemClicklisteners {
        fun onItemClick(position: Int?)
        fun onItemPlus(position: Int?)
        fun ontItemMinus(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(context).inflate(R.layout.player_row_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(arrayList[position], position)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    open inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var player_img = view.findViewById<CircleImageView>(R.id.player_img)
        var tvName = view.findViewById<AppCompatTextView>(R.id.tvName)
        var tvTeamName = view.findViewById<AppCompatTextView>(R.id.tvTeamName)
        var tvCounter = view.findViewById<AppCompatTextView>(R.id.tvCounter)
        var btnMinimise = view.findViewById<AppCompatImageView>(R.id.btnMinimise)
        var btnMaximise = view.findViewById<AppCompatImageView>(R.id.btnMaximise)
        fun bindData(data: player_data, position: Int) {
            player_img.loadImageCustomPlaceHolder(data.image)
            tvName.text = data.name
            tvTeamName.text = data.team_name
            tvCounter.text = data.count.toString()
            itemView.setOnClickListener { listener.onItemClick(position) }
            btnMinimise.setOnClickListener { listener.ontItemMinus(position) }
            btnMaximise.setOnClickListener { listener.onItemPlus(position) }
        }

    }
}


