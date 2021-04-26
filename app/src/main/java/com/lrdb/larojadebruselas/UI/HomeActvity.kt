package com.lrdb.larojadebruselas.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lrdb.larojadebruselas.Model.Player
import com.lrdb.larojadebruselas.Networking.LaRojaService
import com.lrdb.larojadebruselas.R
import com.lrdb.larojadebruselas.View.HomePlayerAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.player_list_recycler_cell.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Asynchronous process using coroutines
        CoroutineScope(Dispatchers.IO).launch {

            Log.d("gabiii!", "Inside Coroutine 1")
            // Here we store the data received in a generic type "response"
            val response = LaRojaService
                .getLaRojaDataService()
                .getLaRojaPlayers()

            // Return to UI thread to process the data and inflate the recyclerView with it
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("gabiii!", "Inside Coroutine 2")
                    val temp = response.body()
                    // Log.d("gabriel", "I did it")

                    val tempList = mutableListOf<Player>()

                    var index = 0
                    temp?.forEach {
                        val active = temp[index].active
                        val bio = temp[index].bio
                        val gamesPlayed = temp[index].gamesPlayed
                        val goalsScored = temp[index].goalsScored
                        val name = temp[index].name
                        val number = temp[index].number
                        val playerPictureUrl = temp[index].playerPictureUrl
                        val position = temp[index].position
                        val profilePictureUrl = temp[index].profilePictureUrl
                        val seasonsActive = temp[index].seasonsActive

                        tempList.add(Player(active, bio, gamesPlayed, goalsScored, name,
                            number, playerPictureUrl, position, profilePictureUrl, seasonsActive))

                        index += 1
                    }

                    recyclerView_home.layoutManager = LinearLayoutManager(this@HomeActvity)
                    recyclerView_home.adapter = HomePlayerAdapter(tempList)
                }
            }
        }

        Log.d("gabiii!", " Outside Coroutine")
    }

    fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.GONE
        } else{
            View.VISIBLE
        }
    }
}