package com.norm.vpnfriendlyclient.presentation.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.norm.vpnfriendlyclient.presentation.components.AddServerDialog
import com.norm.vpnfriendlyclient.presentation.components.BottomNavigationItem
import com.norm.vpnfriendlyclient.presentation.components.VpnBottomNavigation
import com.norm.vpnfriendlyclient.presentation.home.HomeScreen
import com.norm.vpnfriendlyclient.presentation.nvgraph.Route
import com.norm.vpnfriendlyclient.presentation.servers.ServersScreen
import com.norm.vpnfriendlyclient.presentation.servers.ServersViewModel

@Composable
fun MainScreen() {
    val vpnNavigationItems = remember {
        listOf(
            BottomNavigationItem(
                icon = Icons.Default.Home,
                title = "Home",
            ),
            BottomNavigationItem(
                icon = Icons.AutoMirrored.Filled.List,
                title = "Servers",
            ),
            BottomNavigationItem(
                icon = Icons.Default.Settings,
                title = "Settings",
            )
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value

    var selectedItem by remember {
        mutableIntStateOf(0)
    }

    var showDialogAdd by remember {
        mutableStateOf(false)
    }

    selectedItem = remember(key1 = backstackState) {
        when (backstackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.ServersScreen.route -> 1
            Route.SettingsScreen.route -> 2
            else -> 0
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    showDialogAdd = true
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add_server",
                    )
                    Text(
                        text = "Add server"
                    )
                }
            }
        },
        bottomBar = {
            VpnBottomNavigation(
                items = vpnNavigationItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route,
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.ServersScreen.route,
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.SettingsScreen.route,
                        )
                    }
                }
            )
        }
    ) { padding ->
        val viewModel = hiltViewModel<MainViewModel>()
        val state = viewModel.state.collectAsState().value

        if (showDialogAdd) {
            AddServerDialog(
                onShowDialog = {
                    showDialogAdd = it
                },
                onAddServer = {
                    viewModel.addServer(it)
                }
            )
        }
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
        ) {
            composable(
                route = Route.HomeScreen.route,
            ) {
                navController.previousBackStackEntry?.savedStateHandle?.get<String>("server")
                    ?.let { key ->
                        viewModel.selectedServer(key)
                    }
                HomeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = padding.calculateTopPadding(),
                            bottom = padding.calculateBottomPadding(),
                        ),
                    state = state,
                    onStartVpn = viewModel::startVpn,
                    onStopVpn = viewModel::stopVpn,
                    onChoiceServer = {
                        navigateToTab(
                            navController = navController,
                            route = Route.ServersScreen.route,
                        )
                    }
                )
            }
            composable(
                route = Route.ServersScreen.route,
            ) {
                val viewModelServers = hiltViewModel<ServersViewModel>()
                val stateServers = viewModelServers.state.value
                ServersScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = padding.calculateBottomPadding(),
                            top = padding.calculateTopPadding(),
                        ),
                    servers = stateServers.servers,
                    navigateToServer = { key ->
                        navigateToServer(
                            navController = navController,
                            key = key,
                        )
                    },
                    onEditClick = {
                        viewModelServers.editServer(it)
                    },
                    onDeleteClick = {
                        viewModelServers.deleteServer(it)
                    },
                )
            }
            composable(
                route = Route.SettingsScreen.route,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = padding.calculateBottomPadding(),
                            top = padding.calculateTopPadding(),
                        )
                ) {
                    Text(
                        text = "This Settings Screen"
                    )
                }
            }
        }
    }
}

private fun navigateToTab(
    navController: NavController,
    route: String,
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToServer(
    navController: NavController,
    key: String,
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("server", key)
    navController.navigate(
        route = Route.HomeScreen.route
    )
}