package com.petcare.presentation.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {

    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = "YOUR_API_KEY" // Replace with your API key
    )

    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()

    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.OnSendMessage -> {
                sendMessage(event.message)
            }
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            val chat = generativeModel.startChat()
            _state.value = _state.value.copy(
                messages = _state.value.messages + Message(message, true),
                isLoading = true
            )

            val response = chat.sendMessage(message)

            _state.value = _state.value.copy(
                messages = _state.value.messages + Message(response.text ?: "", false),
                isLoading = false
            )
        }
    }
}
