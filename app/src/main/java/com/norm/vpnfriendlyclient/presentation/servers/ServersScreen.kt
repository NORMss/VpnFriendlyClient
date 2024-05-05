package com.norm.vpnfriendlyclient.presentation.servers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.norm.vpnfriendlyclient.domain.VpnKey
import com.norm.vpnfriendlyclient.presentation.components.ServerCard
import androidx.compose.ui.tooling.preview.Preview
import com.norm.vpnfriendlyclient.presentation.medium_padding
import com.norm.vpnfriendlyclient.presentation.smale_padding

@Composable
fun ServersScreen(
    servers: List<VpnKey>,
    onServerClick: (VpnKey) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(smale_padding),
        contentPadding = PaddingValues(medium_padding),
    ) {
        items(servers) { item ->
            ServerCard(
                vpnKey = item,
                onClick = {
                    onServerClick(item)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewServersScreen() {
    ServersScreen(
        listServer,
        {

        }
    )
}

val listServer = listOf(
    VpnKey(
        "ss://123",
        null,
        null,
    ),
    VpnKey(
        "ss://123",
        "My server",
        "ru",
    ),
    VpnKey(
        "ss://123",
        "Work",
        "us",
    ),
    VpnKey(
        "ss://123",
        "Education Server",
        "gb",
    ),
    VpnKey(
        "ss://123",
        "Hello, World Hello, World Hello, World",
        "us",
    ),
)