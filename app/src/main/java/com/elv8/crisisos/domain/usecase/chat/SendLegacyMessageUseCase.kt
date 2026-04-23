package com.elv8.crisisos.domain.usecase.chat

import android.os.Build
import com.elv8.crisisos.domain.model.ChatMessage
import com.elv8.crisisos.domain.model.MessageStatus
import com.elv8.crisisos.domain.model.MessageType
import com.elv8.crisisos.domain.repository.MeshRepository
import com.elv8.crisisos.domain.repository.IdentityRepository
import kotlinx.coroutines.flow.firstOrNull
import java.util.UUID
import javax.inject.Inject

class SendLegacyMessageUseCase @Inject constructor(
    private val repository: MeshRepository,
    private val identityRepository: IdentityRepository
) {
    suspend operator fun invoke(content: String) {
        val identity = identityRepository.getIdentity().firstOrNull()
        val alias = identity?.alias ?: "Survivor_${Build.MODEL}"
        val senderId = identity?.crsId ?: UUID.randomUUID().toString()

        val newMessage = ChatMessage(
            id = UUID.randomUUID().toString(),
            senderId = senderId,
            senderAlias = alias,
            content = content,
            timestamp = System.currentTimeMillis(),
            deliveryStatus = MessageStatus.SENDING,
            hopsCount = 0,
            isOwn = true,
            messageType = MessageType.TEXT
        )

        repository.sendMessage(newMessage)
    }
}
