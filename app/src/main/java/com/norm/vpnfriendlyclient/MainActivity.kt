package com.norm.vpnfriendlyclient

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.norm.vpnfriendlyclient.presentation.mainScreen.MainScreen
import com.norm.vpnfriendlyclient.presentation.mainScreen.MainViewModel
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
            val viewModel = hiltViewModel<MainViewModel>()
            val state by viewModel.state.collectAsState()
            LaunchedEffect(key1 = state.isVpnRunning) {
                if (state.isVpnRunning) {
                    Toast.makeText(
                        applicationContext,
                        "You're connected",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            LaunchedEffect(key1 = state.errorMessage) {
                state.errorMessage?.let { message ->
                    Toast.makeText(
                        applicationContext,
                        message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            VpnFriendlyClientTheme {
                MainScreen(
                    state = state,
                    viewModel::startVpn,
                    viewModel::stopVpn,
                )
            }
        }
    }
}