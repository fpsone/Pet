package com.petcare.presentation.ui.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.petcare.presentation.ui.common.AuroraBackground
import com.petcare.presentation.ui.common.GlassmorphismCard

data class Message(val text: String, val isFromUser: Boolean)

@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    var text by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        AuroraBackground()
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.messages) { message ->
                    MessageBubble(message = message)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type a message") }
                )
                IconButton(onClick = { viewModel.onEvent(ChatEvent.OnSendMessage(text)) }) {
                    Icon(Icons.Filled.Send, contentDescription = "Send")
                }
            }
        }
    }
}

@Composable
fun MessageBubble(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (message.isFromUser) Arrangement.End else Arrangement.Start
    ) {
        GlassmorphismCard(
            modifier = Modifier.clip(RoundedCornerShape(16.dp))
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(16.dp),
                color = Color.White
            )
        }
    }
}