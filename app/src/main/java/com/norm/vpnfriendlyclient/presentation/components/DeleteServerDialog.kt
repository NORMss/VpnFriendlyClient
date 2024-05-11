package com.norm.vpnfriendlyclient.presentation.components

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.norm.vpnfriendlyclient.domain.model.VpnKey
import com.norm.vpnfriendlyclient.presentation.large_rounded
import com.norm.vpnfriendlyclient.presentation.medium_padding
import com.norm.vpnfriendlyclient.presentation.smale_padding


@Composable
fun DeleteServerDialog(
    vpnKey: VpnKey,
    onDeleteClick: () -> Unit,
    onShowDialog: (Boolean) -> Unit,
) {
    Dialog(
        onDismissRequest = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = medium_padding)
                .clip(RoundedCornerShape(large_rounded))
                .background(MaterialTheme.colorScheme.surface)
                .padding(medium_padding)
        ) {
            Text(
                text = "Delete Server",
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(
                modifier = Modifier
                    .height(medium_padding)
            )
            Text(
                text = "Delete the \"${vpnKey.name}\" server?"
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
                        onDeleteClick()
                        onShowDialog(false)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                    ),
                ) {
                    Text(
                        text = "Delete",
                        color = MaterialTheme.colorScheme.onError,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDeleteServerDialog() {
    DeleteServerDialog(
        vpnKey = VpnKey(
            "My Server",
            "My Server",
            null,
        ),
        onDeleteClick = {

        },
        onShowDialog = {

        }
    )

}