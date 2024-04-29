package com.norm.vpnfriendlyclient.presentation.mainScreen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.norm.vpnfriendlyclient.presentation.medium_padding
import com.norm.vpnfriendlyclient.presentation.smale_padding

@Composable
fun MainScreen(
) {

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

                }
            ) {
                Text(
                    text = "Start VPN"
                )
            }
            Spacer(modifier = Modifier.height(smale_padding))
            Button(
                onClick = {

                }
            ) {
                Text(
                    text = "Stop VPN"
                )
            }
        }
    }
}