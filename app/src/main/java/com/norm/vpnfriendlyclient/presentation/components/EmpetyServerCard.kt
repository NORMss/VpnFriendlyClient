package com.norm.vpnfriendlyclient.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import com.norm.vpnfriendlyclient.presentation.height_current_server_box
import com.norm.vpnfriendlyclient.presentation.medium_rounded
import com.norm.vpnfriendlyclient.presentation.smale_padding
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmptyServerCard(
    onServerClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height_current_server_box)
            .clip(RoundedCornerShape(medium_rounded))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .clickable {
                onServerClick()
            }
            .padding(horizontal = smale_padding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Pls to choice server",
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
fun PreviewEmptyServerCard() {
    EmptyServerCard(
        {}
    )
}