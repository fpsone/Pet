package com.petcare.presentation.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.petcare.presentation.ui.common.AuroraBackground
import com.petcare.presentation.ui.common.glassmorphism
import com.petcare.presentation.ui.home.components.AiDiaryDialog
import com.petcare.presentation.ui.home.widgets.AIDiaryWidget
import com.petcare.presentation.ui.home.widgets.HealthVitalsWidget
import com.petcare.presentation.ui.home.widgets.HeroWidget
import com.petcare.presentation.ui.home.widgets.LiveActivityWidget
import com.petcare.presentation.ui.home.widgets.LocationSnapshotWidget

private const val GRID_COLUMNS = 4

sealed class HomeWidget(val span: GridItemSpan) {
    object Hero : HomeWidget(GridItemSpan(2))
    object LiveActivity : HomeWidget(GridItemSpan(2))
    object HealthVitals : HomeWidget(GridItemSpan(1))
    object LocationSnapshot : HomeWidget(GridItemSpan(2))
    object AIDiary : HomeWidget(GridItemSpan(2))
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val widgets = listOf(
        HomeWidget.Hero,
        HomeWidget.LiveActivity,
        HomeWidget.HealthVitals,
        HomeWidget.LocationSnapshot,
        HomeWidget.AIDiary
    )

    Box(modifier = Modifier.fillMaxSize()) {
        AuroraBackground()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate("chat") },
                    shape = CircleShape,
                    modifier = Modifier.glassmorphism(shape = CircleShape)
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Chat, contentDescription = "Chat with Pet Pal AI")
                }
            }
        ) { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(GRID_COLUMNS),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(widgets, span = { it.span }) { widget ->
                    when (widget) {
                        HomeWidget.Hero -> HeroWidget(state.pet)
                        HomeWidget.LiveActivity -> LiveActivityWidget()
                        HomeWidget.HealthVitals -> HealthVitalsWidget(state.batteryPercentage)
                        HomeWidget.LocationSnapshot -> LocationSnapshotWidget(state.petLocation)
                        HomeWidget.AIDiary -> AIDiaryWidget { viewModel.onEvent(HomeEvent.AiDiaryClicked) }
                    }
                }
            }
        }
        AiDiaryDialog(
            isDialogVisible = state.isAiDiaryDialogVisible,
            isLoading = state.isDiaryLoading,
            diaryText = state.aiDiaryText,
            onDismiss = { viewModel.onEvent(HomeEvent.DismissAiDiaryDialog) }
        )
    }
}
