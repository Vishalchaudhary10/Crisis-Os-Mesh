package com.elv8.crisisos.core.network.mesh

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.Flow
import java.io.File
import com.google.android.gms.nearby.connection.Payload
import com.elv8.crisisos.data.remote.mesh.ConnectedPeer

interface IMeshConnectionManager {
    val connectedPeers: StateFlow<Map<String, ConnectedPeer>>
    val isAdvertising: StateFlow<Boolean>
    val isDiscovering: StateFlow<Boolean>
    val peerCount: Flow<Int>
    val pendingAliases: MutableMap<String, String>

    fun getDiscoveryRestartCount(): Int
    fun getLocalAlias(): String
    fun getLocalCrsId(): String
    fun setLocalCrsId(crsId: String)
    fun updateLastSeen(endpointId: String)
    fun startMesh(alias: String)
    fun startAdvertising()
    fun startDiscovery()
    fun getMeshDebugState(): String
    fun stopAdvertising()
    fun stopDiscovery()
    fun sendFilePayload(endpointId: String, filePayload: Payload, fileId: String)
    fun sendFileToEndpoint(endpointId: String, file: File, fileId: String): Long
    fun stopAll()
    fun disconnectFromEndpoint(endpointId: String)
}
