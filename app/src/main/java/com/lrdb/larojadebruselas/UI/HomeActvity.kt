package com.lrdb.larojadebruselas.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.lrdb.larojadebruselas.Model.PlayerListView
import com.lrdb.larojadebruselas.Networking.LaRojaService
import com.lrdb.larojadebruselas.R
import com.lrdb.larojadebruselas.View.HomePlayerAdapter
import kotlinx.android.synthetic.main.activity_home.*
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

            // Here we store the data received in a generic type "response"
            val response = LaRojaService
                                                    .getLaRojaDataService()
                                                    .getLaRojaPlayers()

            // Return to UI thread to process the data and inflate the recyclerView with it
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val temp = response.body()
                    Log.d("gabriel", "I did it")

                    var tempList = mutableListOf<PlayerListView>()

                    var index = 0
                    temp?.forEach {
                        var imageUrl = temp[index].profilePictureUrl
                        val name = temp[index].name
                        tempList.add(PlayerListView(imageUrl, name))
                        index += 1
                    }

                    recyclerView_home.layoutManager = LinearLayoutManager(this@HomeActvity)
                    recyclerView_home.adapter = HomePlayerAdapter(tempList)
                }
            }

        }


    }
}