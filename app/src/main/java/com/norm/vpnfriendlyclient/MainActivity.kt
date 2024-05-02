package com.norm.vpnfriendlyclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.norm.vpnfriendlyclient.presentation.mainScreen.MainScreen
import com.norm.vpnfriendlyclient.presentation.mainScreen.MainState
import com.norm.vpnfriendlyclient.presentation.mainScreen.MainViewModel
import com.norm.vpnfriendlyclient.ui.theme.VpnFriendlyClientTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            VpnFriendlyClientTheme {
                MainScreen(
                    state = MainState(),
                    viewModel::startVpn,
                    viewModel::stopVpn,
                )
            }
        }
    }
}