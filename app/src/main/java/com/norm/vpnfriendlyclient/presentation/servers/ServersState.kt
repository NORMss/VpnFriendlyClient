package com.norm.vpnfriendlyclient.presentation.servers

import com.norm.vpnfriendlyclient.domain.model.VpnKey

data class ServersState(
    val servers: List<VpnKey> = emptyList(),
)