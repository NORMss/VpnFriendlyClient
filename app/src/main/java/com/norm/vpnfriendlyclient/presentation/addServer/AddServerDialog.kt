package com.norm.vpnfriendlyclient.presentation.addServer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.norm.vpnfriendlyclient.domain.VpnKey
import com.norm.vpnfriendlyclient.presentation.large_rounded
import com.norm.vpnfriendlyclient.presentation.medium_padding
import com.norm.vpnfriendlyclient.presentation.smale_padding

@Composable
fun AddServerDialog(
    onShowDialog: (Boolean) -> Unit,
    onAddServer: (VpnKey) -> Unit,
) {
    val clipBoardManager = LocalClipboardManager.current

    var key by remember {
        mutableStateOf(
            clipBoardManager.getText()?.text.let { text ->
                text?.contains("""ss://[^\s@]+@\S+\.\w+""".toRegex()).let {
                    if (it == true) {
                        text
                    } else {
                        ""
                    }
                } ?: ""
            }
        )
    }

    var name by remember {
        mutableStateOf("")
    }
    var country by remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = {

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = medium_padding)
                .clip(RoundedCornerShape(large_rounded))
                .background(MaterialTheme.colorScheme.surface)
                .padding(medium_padding)
        ) {
            OutlinedTextField(
                value = key,
                onValueChange = {
                    key = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Vpn Key",
                    )
                },
                maxLines = 1,
            )
            Spacer(
                modifier = Modifier
                    .height(smale_padding)
            )
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Server name",
                    )
                },
                maxLines = 1,
            )
            Spacer(
                modifier = Modifier
                    .height(smale_padding)
            )
            OutlinedTextField(
                value = country,
                onValueChange = {
                    country = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Сountry",
                    )
                },
                maxLines = 1,
            )
            Spacer(
                modifier = Modifier
                    .height(medium_padding)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                OutlinedButton(
                    onClick = {
                        onShowDialog(false)
                    }
                ) {
                    Text(
                        text = "Chancel"
                    )
                }
                Spacer(
                    modifier = Modifier
                        .width(smale_padding)
                )
                Button(
                    onClick = {
                        onAddServer(
                            VpnKey(
                                key = key,
                                name = name,
                                country = country,
                            )
                        )
                        onShowDialog(false)
                    }
                ) {
                    Text(
                        text = "Add Server"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAddServerDialog() {
    AddServerDialog(
        {

        }
    ) {

    }
}