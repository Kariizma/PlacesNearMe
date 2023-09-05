package com.example.placesnearme.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.placesnearme.model.Place
import com.example.placesnearme.ui.theme.PlacesNearMeTheme
import com.example.placesnearme.viewmodel.PlacesViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PlacesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "place_list") {
                composable("place_list") {
                    PlacesNearMeTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            val places = viewModel.places.collectAsState().value
                            val imagesMap = viewModel.imagesMap.collectAsState().value
                            val searchQuery = remember { mutableStateOf("") }

                            Column {
                                SearchBar(
                                    query = searchQuery.value,
                                    onQueryChange = {
                                        searchQuery.value = it
                                    }
                                )
                                val filteredPlaces = places.filter { place ->
                                    place.name.contains(searchQuery.value, ignoreCase = true)
                                }
                                PlacesList(places = filteredPlaces, imagesMap = imagesMap) { selectedPlace ->
                                    navController.navigate("place_details/${selectedPlace.id}")
                                }
                            }
                        }
                    }
                }

                composable("place_details/{placeId}") { backStackEntry ->
                    val placeId = backStackEntry.arguments?.getString("placeId")
                    val place = viewModel.getPlaceById(placeId)
                    val imageUrl = viewModel.imagesMap.collectAsState().value[placeId]
                    if (place != null) {
                        PlaceDetailsScreen(navController = navController, place = place, imageUrl = imageUrl)
                    } else {
                        // Handle the scenario where the place is not found (maybe show an error UI)
                    }
                }
            }
        }
    }

    @Composable
    fun PlacesList(places: List<Place>, imagesMap: Map<String, String>, onClick: (Place) -> Unit) {
        LazyColumn {
            items(places.size) { index ->
                val place = places[index]
                PlaceListItem(
                    place = place,
                    imageUrl = imagesMap[place.id],
                    onClick = onClick
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchBar(
        query: String,
        onQueryChange: (String) -> Unit
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            label = { Text(text = "Search Places") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}
