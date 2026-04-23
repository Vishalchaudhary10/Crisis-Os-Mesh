package com.elv8.crisisos.core.network.mesh

sealed class DomainSendResult {
    object Sent : DomainSendResult()
    object Queued : DomainSendResult()
    object Failed : DomainSendResult()
}
