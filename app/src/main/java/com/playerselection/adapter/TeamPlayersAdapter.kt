package com.playerselection.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.playerselection.ApiResponse.player_data
import com.playerselection.R

class TeamPlayersAdapter(
    val context: Context,
    var arrayList: ArrayList<player_data>,
    val listener: TeamPlayersAdapter.onItemClicklisteners
) : RecyclerView.Adapter<TeamPlayersAdapter.CustomViewHolder>() {
    open inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgPlayerSelection = view.findViewById<AppCompatImageView>(R.id.imgPlayerSelection)
        fun bindData(s: player_data, position: Int) {

            itemView.setOnClickListener { listener.onItemClick(position) }
        }

    }

    interface onItemClicklisteners {
        fun onItemClick(position: Int?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_selection_icon, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(arrayList[position], position)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}


