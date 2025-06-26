package com.example.pet.presentation.ui.chat

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.pet.domain.model.ChatMessage
import com.example.pet.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val petRepository: PetRepository
) : ViewModel() {

    val chatMessages = mutableStateListOf<ChatMessage>()

    fun sendMessage(message: String) {
        chatMessages.add(ChatMessage(message, true))
        // TODO: Get the response from the Gemini API
    }
}
