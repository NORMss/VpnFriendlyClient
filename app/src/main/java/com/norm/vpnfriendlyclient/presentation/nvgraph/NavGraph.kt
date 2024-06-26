package com.norm.vpnfriendlyclient.presentation.nvgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.norm.vpnfriendlyclient.presentation.mainScreen.MainScreen
import com.norm.vpnfriendlyclient.presentation.nvgraph.Route.VpnNavigatorScreen

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        navigation(
            route = Route.VpnNavigation.route,
            startDestination = VpnNavigatorScreen.route,
        ) {
            composable(
                route = VpnNavigatorScreen.route,
            ) {
                MainScreen()
            }
        }
    }
}