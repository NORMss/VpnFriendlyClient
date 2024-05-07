package com.norm.vpnfriendlyclient

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.norm.vpnfriendlyclient.presentation.nvgraph.NavGraph
import com.norm.vpnfriendlyclient.presentation.nvgraph.Route
import com.norm.vpnfriendlyclient.ui.theme.VpnFriendlyClientTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT,
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            VpnFriendlyClientTheme {
                val startDestination = Route.VpnNavigation.route
                NavGraph(startDestination = startDestination)
            }
        }
    }
}