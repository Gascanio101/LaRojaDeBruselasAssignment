package com.lrdb.larojadebruselas.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lrdb.larojadebruselas.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_profile.*
import kotlinx.android.synthetic.main.player_list_recycler_cell.view.*

class PlayerProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_profile)


        /*intent.getBooleanExtra("active")
        intent.getExtra("bio", playerList[position].bio)
        intent.getExtra("gamesPlayed", playerList[position].gamesPlayed)
        intent.getExtra("goalsScored", playerList[position].goalsScored)

        intent.getExtra("playerPictureUrl", playerList[position].playerPictureUrl)
        intent.getExtra("position", playerList[position].position)
        intent.getExtra("profilePictureUrl", playerList[position].profilePictureUrl)
        intent.getExtra("seasonsActive", playerList[position].seasonsActive)*/


        val profileName = intent.getStringExtra("name").toString()
        profileTextView_name.text = profileName

        val profileNumber = intent.getIntExtra("number", 0)
        profile_textView_number.text = profileNumber.toString()

        val profilePosition = intent.getStringExtra("position").toString()
        profile_textView_position.text = profilePosition

        val profileActive = intent.getBooleanExtra("active", true)
        when (profileActive) {
            true -> profile_textView_active.text = "Active"
            else -> profile_textView_active.text = "Inactive"
        }

        val profilePictureUrl = intent.getStringExtra("playerPictureUrl").toString()
        Picasso.with(this)
                .load(profilePictureUrl)
                .fit()
                .into(imageView_profilePicture)
    }
}