package com.example.placesnearme.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.placesnearme.R
import com.example.placesnearme.model.Place
import com.example.placesnearme.ui.theme.PlacesNearMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlacesNearMeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mockPlaces = listOf(
                        Place(
                            name = "Beautiful Place",
                            id = "aaa",
                            address = "123 Compose Street",
                            price = "$$",
                            reviews = 20,
                            stars = 3
                        )
                    )
                    PlacesList(places = mockPlaces)
                }
            }
        }
    }

    @Composable
    fun PlacesList(places: List<Place>) {
        LazyColumn {
            items(places) { place ->
                PlaceListItem(
                    name = place.name,
                    address = place.address,
                    price = place.price,
                    reviews = place.reviews,
                    stars = place.stars,
                    imageResId = R.drawable.ic_launcher_foreground,
                )
            }
        }
    }

    @Composable
    fun PlaceListItem(name: String, address: String, price: String, reviews: Int, stars: Int, imageResId: Int) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Place Image",
                modifier = Modifier
                    .size(60.dp, 60.dp)
                    .background(MaterialTheme.colorScheme.onPrimary)
            )
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
        PlacesList(places = mockPlaces)
    }
}
