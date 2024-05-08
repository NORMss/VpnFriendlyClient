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
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height_current_server_box)
            .clip(RoundedCornerShape(medium_rounded))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = smale_padding)
                .weight(1f),
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
}

@Preview
@Composable
fun PreviewServerCard() {
    ServerCard(
        vpnKey = VpnKey(
            "ss://123",
            "My Russia Server",
            "ru",
        )
    ) {

    }
}