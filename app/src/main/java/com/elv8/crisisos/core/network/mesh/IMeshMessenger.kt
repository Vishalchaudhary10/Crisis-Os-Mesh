package com.elv8.crisisos.core.network.mesh

import com.elv8.crisisos.data.dto.MeshPacket
import com.elv8.crisisos.data.remote.mesh.SendResult
import com.elv8.crisisos.domain.model.ChatMessage

interface IMeshMessenger {
    fun setLocalDeviceId(id: String)
    suspend fun send(packet: MeshPacket): SendResult
    suspend fun sendChatMessage(message: ChatMessage): DomainSendResult
}
