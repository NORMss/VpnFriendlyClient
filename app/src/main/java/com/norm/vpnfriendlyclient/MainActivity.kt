package com.norm.vpnfriendlyclient

import android.content.Intent
import android.net.VpnService
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.norm.vpnfriendlyclient.service.NormVpnService
import com.norm.vpnfriendlyclient.ui.theme.VpnFriendlyClientTheme

class MainActivity : ComponentActivity() {

    private lateinit var prepareVpnLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareVpnLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    startVpnService()
                }
            }
        setContent {
            VpnFriendlyClientTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(onClick = {
                            if (hasPermission()) {
                                startVpnService()
                            } else {
                                askPermission()
                            }
                        }
                        ) {
                            Text(
                                text = "Start"
                            )
                        }
                        Button(onClick = {
                            stopVpnService()
                        }
                        ) {
                            Text(
                                text = "Stop"
                            )
                        }
                    }
                }
            }
        }
    }

    private fun startVpnService() {
        Intent(this, NormVpnService::class.java).also {
            it.action = NormVpnService.Actions.START_VPN_SERVICE.toString()
            startService(it)
        }
    }

    private fun stopVpnService() {
        Intent(this, NormVpnService::class.java).also {
            it.action = NormVpnService.Actions.STOP_VPN_SERVICE.toString()
            startService(it)
        }
    }

    private fun askPermission() {
        val intent = VpnService.prepare(this)
        intent?.let {
            prepareVpnLauncher.launch(it)
        }
    }

    private fun hasPermission(): Boolean {
        return VpnService.prepare(this) == null
    }
}