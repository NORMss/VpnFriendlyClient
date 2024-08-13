package com.norm.vpnfriendlyclient.presentation.nvgraph

import kotlinx.serialization.Serializable

//sealed class Route(
//    val route: String,
//) {
//    data object HomeScreen : Route(route = "homeScreen")
//    data object ServersScreen : Route(route = "serversScreen")
//    data object SettingsScreen : Route(route = "settingsScreen")
//    data object VpnNavigation : Route(route = "vpnNavigation")
//    data object VpnNavigatorScreen : Route(route = "vpnNavigatorScreen")
//}

@Serializable
sealed class Route {
    @Serializable
    data class HomeScreen(
        val key: String? = null,
    ) : Route()

    @Serializable
    data object ServersScreen : Route()

    @Serializable
    data object SettingsScreen : Route()

    @Serializable
    data object VpnNavigation : Route()

    @Serializable
    data object VpnNavigatorScreen : Route()
}