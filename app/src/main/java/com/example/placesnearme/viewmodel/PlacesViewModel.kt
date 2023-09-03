package com.example.placesnearme.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.placesnearme.api.PlacesApi
import com.example.placesnearme.model.Places
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel
@Inject constructor(private val placesApi: PlacesApi, application: Application):
    AndroidViewModel(application) {

    private val _places = MutableStateFlow<List<Places>>(listOf())

    init {
        getPlaces()
    }


    private fun getPlaces() {
        viewModelScope.launch {
            Log.v("API Data:", "Loading...")
            try {
                placesApi.getPlaces()
                Log.v("API Data:", "Success")
            } catch (e: Exception)
            {
                Log.e("API Data:", "Error: $e")
            }
        }
    }
}