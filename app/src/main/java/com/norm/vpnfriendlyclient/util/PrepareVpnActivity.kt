package com.norm.vpnfriendlyclient.util

import android.net.VpnService
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.norm.vpnfriendlyclient.data.AndroidNormVpnServiceController

class PrepareVpnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                AndroidNormVpnServiceController(applicationContext).startVpn()
            }
            finish()
        }
            .also {
                VpnService.prepare(applicationContext)?.let { intent ->
                    it.launch(intent)
                }
                finish()
            }
    }
}