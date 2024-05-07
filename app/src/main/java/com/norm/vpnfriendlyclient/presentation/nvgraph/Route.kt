package com.norm.vpnfriendlyclient.presentation.nvgraph

sealed class Route(
    val route: String,
) {
    data object HomeScreen : Route(route = "homeScreen")
    data object ServersScreen : Route(route = "serversScreen")
    data object SettingsScreen : Route(route = "settingsScreen")
    data object VpnNavigation : Route(route = "vpnNavigation")
    data object VpnNavigatorScreen : Route(route = "vpnNavigatorScreen")
}