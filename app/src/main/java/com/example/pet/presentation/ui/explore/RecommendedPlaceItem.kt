package com.example.pet.presentation.ui.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pet.domain.model.RecommendedPlace

@Composable
fun RecommendedPlaceItem(place: RecommendedPlace) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row {
            AsyncImage(
                model = place.imageUrl,
                contentDescription = place.name
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = place.name)
                Text(text = place.description)
                Text(text = "Rating: ${place.rating}")
            }
        }
    }
}
