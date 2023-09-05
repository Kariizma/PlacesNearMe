package com.example.placesnearme.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.placesnearme.R
import com.example.placesnearme.model.Place


@OptIn(ExperimentalCoilApi::class)
@Composable
fun PlaceListItem(
    place: Place,
    imageUrl: String?,
    onClick: (Place) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
            .clickable { onClick(place) }
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        ) {
            Text(text = place.name, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = place.address)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                StarsLayout(rating = place.stars)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = place.reviews.toString())
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = place.price)
            }
        }

        imageUrl?.let {
            Image(
                painter = rememberImagePainter(data = it),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp, 80.dp)
            )
        }
    }
}

@Composable
fun StarsLayout(rating: Int, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        for (i in 0 until 5) {
            Image(
                painter = if (i < rating) {
                    painterResource(id = R.drawable.ic_star_filled)
                } else {
                    painterResource(id = R.drawable.ic_star_border)
                },
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}