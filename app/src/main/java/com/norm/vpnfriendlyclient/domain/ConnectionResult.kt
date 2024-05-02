package com.norm.vpnfriendlyclient.domain

sealed interface VpnRunResult {
    data object VpnRunEstablished : VpnRunResult

    data class Error(
        val message: String,
    ) : VpnRunResult
}