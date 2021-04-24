package com.lrdb.larojadebruselas.Networking

import com.lrdb.larojadebruselas.Model.LaRojaApiResponse.PlayerListResponse
import retrofit2.Response
import retrofit2.http.GET

interface LaRojaApi {

    @GET("/players")
    suspend fun getLaRojaPlayers() : Response<PlayerListResponse>
}