package com.elv8.crisisos.domain.usecase.chat

import com.elv8.crisisos.core.network.mesh.IMeshConnectionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

data class ConnectionState(
    val peerCount: Int,
    val isConnected: Boolean
)

class ObserveConnectionStateUseCase @Inject constructor(
    private val connectionManager: IMeshConnectionManager
) {
    operator fun invoke(): Flow<ConnectionState> {
        return connectionManager.peerCount.map { count ->
            ConnectionState(peerCount = count, isConnected = count > 0)
        }
    }
}
