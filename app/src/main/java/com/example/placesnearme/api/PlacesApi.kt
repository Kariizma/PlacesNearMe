package com.example.placesnearme.api

import com.example.placesnearme.model.Places
import retrofit2.http.GET
import retrofit2.http.Path

interface PlacesApi {

    @GET("data/places")
    suspend fun getPlaces(): List<Places>

}
