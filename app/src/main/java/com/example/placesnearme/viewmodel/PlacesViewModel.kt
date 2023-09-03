package com.example.placesnearme.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.placesnearme.api.PlacesApi
import com.example.placesnearme.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel
@Inject constructor(private val placesApi: PlacesApi, application: Application):
    AndroidViewModel(application) {

    private var _places = MutableStateFlow<List<Place>>(listOf())
    val places: StateFlow<List<Place>> = _places

    private val _imagesMap = MutableStateFlow<Map<String, String>>(emptyMap())
    val imagesMap: StateFlow<Map<String, String>> = _imagesMap

    init {
        getPlaces()
    }


    private fun getPlaces() {
        viewModelScope.launch {
            Log.v("API Data:", "Loading...")
            try {
                val placesList = placesApi.getPlaces().places
                val imagesMapTemp = mutableMapOf<String, String>()

                placesList.forEach { place ->
                    val imageResponse = placesApi.getImageById(place.id)
                    imagesMapTemp[place.id] = imageResponse.img
                }

                _places.value = placesList
                _imagesMap.value = imagesMapTemp
                Log.v("API Data:", "Success")
            } catch (e: Exception) {
                Log.e("API Data:", "Error: $e")
            }
        }
    }
}