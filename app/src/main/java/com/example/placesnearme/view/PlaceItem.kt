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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.placesnearme.R


@Composable
fun PlaceListItem(
    name: String,
    address: String,
    stars: Int,
    reviews: Int,
    price: String,
    imageResId: Int,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
            .clickable { onClick?.invoke() }
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        ) {
            Text(text = name, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = address)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                StarsLayout(rating = stars)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = reviews.toString())
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = price)
            }
        }

        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp, 80.dp)
        )
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

@Preview(showBackground = true, name = "Place List Item Preview")
@Composable
fun PlaceListItemPreview() {
    // Provide mock data for the preview
    PlaceListItem(
        name = "Beautiful Place",
        address = "123 Compose Street",
        stars = 3,
        reviews = 20,
        price = "$$",
        imageResId = R.drawable.ic_launcher_foreground
    )
}
