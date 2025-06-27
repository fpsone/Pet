package com.petcare.presentation.ui.home.widgets

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.petcare.presentation.ui.common.glassmorphism
import com.petcare.presentation.ui.common.GlassmorphismCard

@Composable
fun LocationSnapshotWidget(petLocation: LatLng?) {
    GlassmorphismCard(
        modifier = Modifier
            .aspectRatio(1f)
    ) {
        if (petLocation != null) {
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(petLocation, 15f)
            }
            LaunchedEffect(petLocation) {
                cameraPositionState.animate(
                    CameraUpdateFactory.newCameraPosition(
                        CameraPosition(petLocation, 15f, 0f, 0f)
                    )
                )
            }
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(zoomControlsEnabled = false, scrollGesturesEnabled = false),
                properties = MapProperties(isTrafficEnabled = false)
            ) {
                Marker(
                    state = remember(petLocation) { MarkerState(position = petLocation) },
                    title = "Pet's Location"
                )
            }
        }
    }
}