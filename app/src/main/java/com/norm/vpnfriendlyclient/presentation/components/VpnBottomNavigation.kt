package com.norm.vpnfriendlyclient.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.norm.vpnfriendlyclient.ui.theme.VpnFriendlyClientTheme

data class BottomNavigationItem(
    val icon: ImageVector,
    val title: String,
)

@Composable
fun VpnBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit,
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = {
                    onItemClick(index)
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                        )
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewNewsBottomNavigation() {
    VpnFriendlyClientTheme {
        VpnBottomNavigation(
            items = listOf(
                BottomNavigationItem(
                    icon = Icons.Default.Home,
                    title = "Home",
                ),
                BottomNavigationItem(
                    icon = Icons.AutoMirrored.Filled.List,
                    title = "Servers",
                ),
                BottomNavigationItem(
                    icon = Icons.Default.Settings,
                    title = "Settings",
                )
            ),
            selected = 0,
            onItemClick = {

            },
        )
    }
}