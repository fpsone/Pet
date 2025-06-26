package com.example.pet.presentation.ui.explore

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.pet.domain.model.RecommendedPlace

@Composable
fun RecommendedPlacesList(places: List<RecommendedPlace>) {
    LazyColumn {
        items(places) {
            RecommendedPlaceItem(place = it)
        }
    }
}
