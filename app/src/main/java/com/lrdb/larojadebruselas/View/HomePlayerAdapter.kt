package com.lrdb.larojadebruselas.View

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lrdb.larojadebruselas.Model.Player
import com.lrdb.larojadebruselas.R
import com.lrdb.larojadebruselas.UI.PlayerProfileActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.player_list_recycler_cell.view.*

class HomePlayerAdapter (var playerList: List<Player>) : RecyclerView.Adapter<HomePlayerAdapter.HomePlayerViewHolder>() {

    class HomePlayerViewHolder (playerView: View) : RecyclerView.ViewHolder(playerView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePlayerViewHolder {
        return HomePlayerViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.player_list_recycler_cell, parent, false))
    }

    override fun onBindViewHolder(holder: HomePlayerViewHolder, position: Int) {

        // Inflate the recyclerView cell with some relevant info.
        holder.itemView.textView_homeName.text = playerList[position].name
        val profilePictureView = holder.itemView.imageView_playerList_picture
        Picasso.with(holder.itemView.context)
            .load(playerList[position].profilePictureUrl)
            .fit()
            .into(profilePictureView)
        holder.itemView.textView_goalsScored.text = playerList[position].goalsScored.toString()
        holder.itemView.imageView_goalIcon.setImageResource(R.drawable.goal_icon)

        // Create an Intent to pass data to the profile activity when tapping the player's cell
        val intent: Intent = Intent(holder.itemView.context, PlayerProfileActivity::class.java)
        holder.itemView.setOnClickListener {

            intent.putExtra("active", playerList[position].active)
            intent.putExtra("bio", playerList[position].bio)
            intent.putExtra("gamesPlayed", playerList[position].gamesPlayed)
            intent.putExtra("goalsScored", playerList[position].goalsScored)
            intent.putExtra("name", playerList[position].name)
            intent.putExtra("number", playerList[position].number)
            intent.putExtra("playerPictureUrl", playerList[position].playerPictureUrl)
            intent.putExtra("position", playerList[position].position)
            intent.putExtra("profilePictureUrl", playerList[position].profilePictureUrl)
            intent.putExtra("seasonsActive", playerList[position].seasonsActive)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return playerList.size
    }
}