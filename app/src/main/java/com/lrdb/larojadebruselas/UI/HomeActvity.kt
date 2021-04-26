package com.lrdb.larojadebruselas.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
        val mutableLiveList= MutableLiveData<MutableList<Player>>()

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
                    val temp = response.body()
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
                    mutableLiveList.value = tempList
                }
            }
        }

        mutableLiveList.observe(
                this,Observer{
            recyclerView_home.layoutManager = LinearLayoutManager(this@HomeActvity)
            recyclerView_home.adapter = HomePlayerAdapter(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // TODO: Implement onClickListeners for the menu's options.
        when (item.itemId) {
            R.id.home_filterActive -> {}
        }

        return super.onOptionsItemSelected(item)
    }
}