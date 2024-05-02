package com.norm.vpnfriendlyclient.domain

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface VpnController {

    val isVpnRunning: StateFlow<Boolean>
    val listKeys: StateFlow<List<VpnKey>>
    val errors: SharedFlow<String>
    fun startVpn()
    fun stopVpn()
}