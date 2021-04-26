package com.lrdb.larojadebruselas.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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


        getPlayerList(0)

    }

    fun getPlayerList(case: Int) {
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
                        val active = it.active
                        val bio = it.bio
                        val gamesPlayed = it.gamesPlayed
                        val goalsScored = it.goalsScored
                        val name = it.name
                        val number = it.number
                        val playerPictureUrl = it.playerPictureUrl
                        val position = it.position
                        val profilePictureUrl = it.profilePictureUrl
                        val seasonsActive = it.seasonsActive

                        tempList.add(Player(active, bio, gamesPlayed, goalsScored, name,
                                number, playerPictureUrl, position, profilePictureUrl, seasonsActive))

                        index += 1
                    }

                    when (case) {
                        0 -> {
                            recyclerView_home.layoutManager = LinearLayoutManager(this@HomeActvity)
                            recyclerView_home.adapter = HomePlayerAdapter(tempList)
                        }
                        1 -> {
                            Log.d("gabo", "Case 1 = $case")
                            Log.d("gabo", "$tempList")
                            val filteredList = tempList.filter { it.active == false }
                            recyclerView_home.layoutManager = LinearLayoutManager(this@HomeActvity)
                            recyclerView_home.adapter = HomePlayerAdapter(filteredList)
                        }
                        2 -> {
                            val filteredList = tempList.filter { it.active == true }
                            recyclerView_home.layoutManager = LinearLayoutManager(this@HomeActvity)
                            recyclerView_home.adapter = HomePlayerAdapter(filteredList)
                        }

                    }

                }
            }
        }
        return 
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var activeToggle = false
        when (item.itemId) {
            R.id.home_filterActive -> {
                if (activeToggle == false) {
                    getPlayerList(1)
                    activeToggle = true
                }

            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.GONE
        } else{
            View.VISIBLE
        }
    }
}