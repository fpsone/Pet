package com.petcare.presentation.ui.chat

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)

sealed class ChatEvent {
    data class OnSendMessage(val message: String) : ChatEvent()
}
