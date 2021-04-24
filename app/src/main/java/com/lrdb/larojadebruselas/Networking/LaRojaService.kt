package com.lrdb.larojadebruselas.Networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LaRojaService {

    private val BASE_URL = "https://la-roja-de-bruselas-api-4j7rrhgcpa-ez.a.run.app/"
    fun getLaRojaDataService() : LaRojaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(LaRojaApi::class.java)
    }
}