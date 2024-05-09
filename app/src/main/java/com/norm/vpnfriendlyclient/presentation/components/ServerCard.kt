package com.norm.vpnfriendlyclient.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.norm.vpnfriendlyclient.R
import com.norm.vpnfriendlyclient.domain.model.VpnKey
import com.norm.vpnfriendlyclient.presentation.height_current_server_box
import com.norm.vpnfriendlyclient.presentation.medium_rounded
import com.norm.vpnfriendlyclient.presentation.smale_padding


@Composable
fun ServerCard(
    vpnKey: VpnKey,
    onServerClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height_current_server_box)
            .clip(RoundedCornerShape(medium_rounded))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(horizontal = smale_padding)
            .clickable {
                onServerClick()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = vpnKey.name ?: "Server",
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = smale_padding
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = vpnKey.country ?: "indefinite",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                )
                Spacer(
                    modifier = Modifier
                        .width(smale_padding)
                )
                Image(
                    painter = painterResource(id = R.drawable.undefined),
                    contentDescription = null,
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                onClick = {
                    onEditClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "edit"
                )
            }
            IconButton(
                onClick = {
                    onDeleteClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewServerCard() {
    ServerCard(
        vpnKey = VpnKey(
            "ss://123",
            "My Russia Server",
            "ru",
        ),
        onServerClick = {

        },
        onDeleteClick = {

        },
        onEditClick = {

        },
    )
}