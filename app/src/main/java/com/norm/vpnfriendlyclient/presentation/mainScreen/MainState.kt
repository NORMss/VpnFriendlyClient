package com.norm.vpnfriendlyclient.presentation.mainScreen

import com.norm.vpnfriendlyclient.domain.model.VpnKey

data class MainState(
    val isVpnRunning: Boolean = false,
    val errorMessage: String? = null,
    val vpnKey: VpnKey? = null,
)