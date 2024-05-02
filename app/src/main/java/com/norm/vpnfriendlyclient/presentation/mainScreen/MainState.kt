package com.norm.vpnfriendlyclient.presentation.mainScreen

import com.norm.vpnfriendlyclient.domain.VpnKey

data class MainState(
    val isVpnRunning: Boolean = false,
    val errorMessage: String? = null,
    val listKeys: List<VpnKey> = emptyList(),
)