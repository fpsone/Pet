package com.petcare.presentation.ui.home

sealed class HomeEvent {
    data object AiDiaryClicked : HomeEvent()
    data object DismissAiDiaryDialog : HomeEvent()
}