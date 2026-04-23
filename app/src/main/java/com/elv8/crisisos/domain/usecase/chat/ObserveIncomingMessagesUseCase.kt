package com.elv8.crisisos.domain.usecase.chat

import com.elv8.crisisos.domain.model.ChatMessage
import com.elv8.crisisos.domain.repository.MeshRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveIncomingMessagesUseCase @Inject constructor(
    private val repository: MeshRepository
) {
    operator fun invoke(): Flow<ChatMessage> = repository.observeIncomingMessages()
}
