package com.norm.vpnfriendlyclient.domain

import com.norm.vpnfriendlyclient.domain.model.VpnKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface VpnController {

    val isVpnRunning: StateFlow<Boolean>
    val listKeys: StateFlow<List<VpnKey>>
    val errors: SharedFlow<String>
    fun startVpn(): Flow<VpnRunResult>
    fun stopVpn(): Flow<VpnRunResult>
}