@file:OptIn(ExperimentalCoilApi::class)

package com.example.placesnearme.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.placesnearme.model.Place
import com.example.placesnearme.ui.theme.PlacesNearMeTheme
import com.example.placesnearme.viewmodel.PlacesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PlacesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlacesNearMeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val places = viewModel.places.collectAsState().value
                    val imagesMap = viewModel.imagesMap.collectAsState().value
                    PlacesList(places = places, imagesMap = imagesMap)
                }
            }
        }
    }

    @Composable
    fun PlacesList(places: List<Place>, imagesMap: Map<String, String>) {
        LazyColumn {
            items(places) { place ->
                PlaceListItem(
                    name = place.name,
                    address = place.address,
                    price = place.price,
                    reviews = place.reviews,
                    stars = place.stars,
                    imageUrl = imagesMap[place.id]
                )
            }
        }
    }

    @Composable
    fun PlaceListItem(name: String, address: String, price: String, reviews: Int, stars: Int, imageUrl: String?) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            imageUrl?.let {
                Image(
                    painter = rememberImagePainter(data = it),
                    contentDescription = "Place Image",
                    modifier = Modifier
                        .size(60.dp, 60.dp)
                        .background(MaterialTheme.colorScheme.onPrimary)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = name, style = MaterialTheme.typography.headlineSmall)
                Text(text = address)
                Text(text = "$price • $reviews reviews • $stars stars")
            }
        }
    }

    @Preview(showBackground = true, name = "Place List Screen Preview")
    @Composable
    fun PlaceListScreenPreview() {
        val mockPlaces = listOf(
            Place(
                name = "Beautiful Place",
                id = "aaa",
                address = "123 Compose Street",
                price = "$$",
                reviews = 20,
                stars = 3,
            )
        )
        val mockImagesMap = mapOf("aaa" to "https://example.com/image.jpg")
        PlacesList(places = mockPlaces, imagesMap = mockImagesMap)
    }
}
