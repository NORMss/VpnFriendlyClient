package com.norm.vpnfriendlyclient.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NormVpnStateReceiver(
    val onStateChanged: (isVpnRunning: Boolean) -> Unit,
) : BroadcastReceiver(
) {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isVpnRunning = intent?.getBooleanExtra("isvpnrunning", false) ?: false
        onStateChanged(isVpnRunning)
    }
}