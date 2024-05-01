package com.norm.vpnfriendlyclient.presentation.mainScreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.norm.vpnfriendlyclient.data.AndroidNormVpnServiceController
import com.norm.vpnfriendlyclient.data.NormVpnStateReceiver
import com.norm.vpnfriendlyclient.presentation.medium_padding
import com.norm.vpnfriendlyclient.presentation.smale_padding
import com.norm.vpnfriendlyclient.service.NormVpnService

@Composable
fun MainScreen(
    context: Context,
) {
    val vpnController = AndroidNormVpnServiceController(context)

    val text by remember {
        mutableStateOf(vpnController.isVpnRunning.value)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(medium_padding),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    vpnController.startVpn()
                }
            ) {
                Text(
                    text = "Start VPN"
                )
            }
            Spacer(modifier = Modifier.height(smale_padding))
            Button(
                onClick = {
                    vpnController.stopVpn()
                }
            ) {
                Text(
                    text = "Stop VPN"
                )
            }
            Text(
                text = "VPN Status: ${text}"
            )
        }
    }
}