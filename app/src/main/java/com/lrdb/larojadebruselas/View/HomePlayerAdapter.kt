package com.lrdb.larojadebruselas.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lrdb.larojadebruselas.Model.PlayerListView
import com.lrdb.larojadebruselas.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.player_list_recycler_cell.view.*

class HomePlayerAdapter (var playerList: List<PlayerListView>) : RecyclerView.Adapter<HomePlayerAdapter.HomePlayerViewHolder>() {

    class HomePlayerViewHolder (playerView: View) : RecyclerView.ViewHolder(playerView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePlayerViewHolder {
        return HomePlayerViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.player_list_recycler_cell, parent, false))
    }

    override fun onBindViewHolder(holder: HomePlayerViewHolder, position: Int) {
        holder.itemView.textView_homeName.text = playerList[position].name
        val profilePictureView = holder.itemView.imageView_playerList_picture
        Picasso.with(holder.itemView.context)
            .load(playerList[position].playerImageUrl)
            .fit()
            .into(profilePictureView)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }
}