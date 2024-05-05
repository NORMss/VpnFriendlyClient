package com.norm.vpnfriendlyclient.presentation.mainScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.norm.vpnfriendlyclient.presentation.home.HomeScreen

@Composable
fun MainScreen(
    state: MainState,
    onStartVpn: () -> Unit,
    onStopVpn: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {

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
        }
    ) { padding ->
        HomeScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding(),
                ),
            state,
            onStartVpn,
            onStopVpn,
        )
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(
        state = MainState(),
        {

        },
        {

        }
    )
}