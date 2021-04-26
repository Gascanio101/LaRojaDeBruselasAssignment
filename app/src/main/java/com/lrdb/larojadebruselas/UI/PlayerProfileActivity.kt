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

        val name = intent.getStringExtra("name").toString()
        profileTextView_name.text = "Name: " + name

        val bio = intent.getStringExtra("bio").toString()
        profile_textView_bio.text = bio

        val gamesPlayed = intent.getIntExtra("gamesPlayed", 0)
        profile_textView_gamesPlayed.text = "Games Played: " + gamesPlayed.toString()

        val goalsScored = intent.getIntExtra("goalsScored", 0)
        profile_textView_goalsScored.text = "Goals scored: " + goalsScored.toString()

        val seasonsActive = intent.getIntExtra("seasonsActive", 0)
        profile_textView_seasonsActive.text = "Seasons Active: " + seasonsActive.toString()

        val profileNumber = intent.getIntExtra("number", 0)
        profile_textView_number.text = "Player number: " + profileNumber.toString()

        val profilePosition = intent.getStringExtra("position").toString()
        profile_textView_position.text = "Position: " + profilePosition

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