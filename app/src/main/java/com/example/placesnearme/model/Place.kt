package com.example.placesnearme.model

data class Place(
    val address: String,
    val id: String,
    val name: String,
    val price: String,
    val reviews: Int,
    val stars: Int,
    val description: String,
)