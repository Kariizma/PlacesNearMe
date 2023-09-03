package com.example.placesnearme.api

import com.example.placesnearme.model.Images
import com.example.placesnearme.model.Places
import retrofit2.http.GET
import retrofit2.http.Path

interface PlacesApi {

    @GET("data/places")
    suspend fun getPlaces(): Places

    @GET("data/img/{id}")
    suspend fun getImageById(@Path("id") id: String): Images
}
