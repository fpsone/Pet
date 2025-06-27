package com.petcare.presentation.ui.walk

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.petcare.data.remote.RecommendedPlace
import com.petcare.presentation.ui.common.GlassmorphismCard
import com.petcare.presentation.ui.common.glassmorphism
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalkScreen(viewModel: WalkViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(Modifier.padding(bottom = 80.dp)) {
                if (state.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    RecommendedPlacesSheet(places = state.recommendedPlaces)
                }
            }
        },
        sheetPeekHeight = 128.dp,
        sheetContainerColor = MaterialTheme.colorScheme.surface,
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            val cameraPositionState = rememberCameraPositionState {
                state.scentTrail.lastOrNull()?.let {
                    position = CameraPosition.fromLatLngZoom(it, 17f)
                }
            }

            LaunchedEffect(state.scentTrail.lastOrNull()) {
                state.scentTrail.lastOrNull()?.let {
                    cameraPositionState.animate(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition(it, 17f, 0f, 0f)
                        )
                    )
                }
            }

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                if (state.isWalking) {
                    Polyline(points = state.scentTrail, color = MaterialTheme.colorScheme.primary, width = 10f)
                    state.scentTrail.lastOrNull()?.let {
                        Marker(state = MarkerState(position = it), title = "Current Location")
                    }
                }
            }

            FloatingActionButton(
                onClick = { viewModel.onEvent(WalkEvent.ToggleWalkingState) },
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = if (state.isWalking) Icons.Filled.Pause else Icons.Rounded.PlayArrow,
                    contentDescription = if (state.isWalking) "Stop Walking" else "Start Walking"
                )
            }

            AnimatedVisibility(
                visible = state.isWalking,
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 16.dp)
            ) {
                ActivityPod(duration = state.currentDuration, distance = state.currentDistance)
            }
        }
    }
}

@Composable
private fun RecommendedPlacesSheet(places: List<RecommendedPlace>) {
    LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 128.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                "Discover Nearby Places",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }
        items(places) { place ->
            RecommendedPlaceCard(place = place)
        }
    }
}

@Composable
private fun RecommendedPlaceCard(place: RecommendedPlace) {
    GlassmorphismCard(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = place.imageUrl,
                contentDescription = place.name,
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(place.name, style = MaterialTheme.typography.titleMedium, color = Color.White)
                Text(place.description, style = MaterialTheme.typography.bodySmall, color = Color.White)
                Text("Rating: ${place.rating} ⭐", style = MaterialTheme.typography.bodySmall, color = Color.White)
            }
        }
    }
}

@Composable
private fun ActivityPod(duration: Long, distance: Double) {
    val formattedTime = remember(duration) {
        String.format(
            "%02d:%02d",
            TimeUnit.SECONDS.toMinutes(duration),
            duration % 60
        )
    }
    val formattedDistance = remember(distance) { String.format("%.2f km", distance) }

    GlassmorphismCard {
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Time", style = MaterialTheme.typography.labelSmall.copy(color = Color.White))
                Text(formattedTime, style = MaterialTheme.typography.titleLarge.copy(color = Color.White))
            }
            Spacer(modifier = Modifier.width(24.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Distance", style = MaterialTheme.typography.labelSmall.copy(color = Color.White))
                Text(formattedDistance, style = MaterialTheme.typography.titleLarge.copy(color = Color.White))
            }
        }
    }
}
