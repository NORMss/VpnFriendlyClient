package com.norm.vpnfriendlyclient.presentation.servers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.norm.vpnfriendlyclient.domain.model.VpnKey
import com.norm.vpnfriendlyclient.presentation.components.DeleteServerDialog
import com.norm.vpnfriendlyclient.presentation.components.EditServerDialog
import com.norm.vpnfriendlyclient.presentation.components.ServerCard
import com.norm.vpnfriendlyclient.presentation.medium_padding
import com.norm.vpnfriendlyclient.presentation.smale_padding

@Composable
fun ServersScreen(
    modifier: Modifier = Modifier,
    servers: List<VpnKey>,
    navigateToServer: (String) -> Unit,
    onDeleteClick: (VpnKey) -> Unit,
    onEditClick: (VpnKey) -> Unit,
) {
    var showDeleteServerDialog by remember {
        mutableStateOf(false)
    }
    var showDialogEdit by remember {
        mutableStateOf(false)
    }

    var deleteServer by remember {
        mutableStateOf(
            VpnKey(
                "",
                null,
                null,
            )
        )
    }
    var editServer by remember {
        mutableStateOf(
            VpnKey(
                "",
                null,
                null,
            )
        )
    }

    if (showDeleteServerDialog) {
        DeleteServerDialog(
            vpnKey = deleteServer,
            onDeleteClick = {
                onDeleteClick(deleteServer)
            },
            onShowDialog = {
                showDeleteServerDialog = it
            }
        )
    }
    if (showDialogEdit) {
        EditServerDialog(
            vpnKey = editServer,
            onEditServer = {
                onEditClick(it)
            },
            onShowDialog = {
                showDialogEdit = it
            },
        )
    }
    LazyColumn(
        modifier = modifier
            .padding(top = smale_padding),
        verticalArrangement = Arrangement.spacedBy(medium_padding),
        contentPadding = PaddingValues(horizontal = medium_padding),
    ) {
        items(servers) { item ->
            ServerCard(
                vpnKey = item,
                onServerClick = {
                    navigateToServer(item.key)
                },
                onEditClick = {
                    editServer = item
                    showDialogEdit = true
                },
                onDeleteClick = {
                    deleteServer = item
                    showDeleteServerDialog = true
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewServersScreen() {
    ServersScreen(
        modifier = Modifier.fillMaxSize(),
        listServer,
        navigateToServer = {

        },
        onDeleteClick = {

        },
        onEditClick = {

        },
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
    VpnKey(
        "ss://123@1.2.3",
        "Server",
        "ru",
    ),
    VpnKey(
        "ss://123@1.2.3",
        "My Server",
        null,
    ),
    VpnKey(
        "ss://123@1.2.3",
        null,
        "us",
    ),
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