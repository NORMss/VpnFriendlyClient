package com.norm.vpnfriendlyclient.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.norm.vpnfriendlyclient.presentation.components.CurrentServerCard
import com.norm.vpnfriendlyclient.presentation.components.EmptyServerCard
import com.norm.vpnfriendlyclient.presentation.mainScreen.MainState
import com.norm.vpnfriendlyclient.presentation.medium_padding
import com.norm.vpnfriendlyclient.presentation.medium_rounded
import com.norm.vpnfriendlyclient.presentation.smale_padding

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: MainState,
    onStartVpn: () -> Unit,
    onStopVpn: () -> Unit,
    onChoiceServer: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = smale_padding)
                .padding(horizontal = medium_padding)
        ) {
            if (state.vpnKey == null) {
                EmptyServerCard(
                    onServerClick = {
                        onChoiceServer()
                    }
                )
            } else {
                CurrentServerCard(
                    vpnKey = state.vpnKey,
                    onServerClick = {
                        onChoiceServer()
                    }
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(medium_padding)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .padding(horizontal = medium_padding)
                .clip(RoundedCornerShape(medium_rounded))
                .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center,
        ) {
            Switch(
                checked = state.isVpnRunning,
                onCheckedChange = {
                    if (state.isVpnRunning) {
                        onStopVpn()
                    } else
                        onStartVpn()
                },
            )
        }
        Spacer(
            modifier = Modifier
                .height(medium_padding)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = smale_padding)
                .padding(horizontal = medium_padding)
                .clip(RoundedCornerShape(medium_rounded))
                .background(MaterialTheme.colorScheme.tertiaryContainer),
        )
    }
}