package com.norm.vpnfriendlyclient.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import androidx.core.app.NotificationCompat
import com.norm.vpnfriendlyclient.R
import com.norm.vpnfriendlyclient.util.CHANNEL_ID
import com.norm.vpnfriendlyclient.util.NOTIFICATION_ID
import java.io.FileInputStream

class NormVpnService : VpnService() {

    private var vpnThread: Thread? = null
    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                Actions.STOP_VPN_SERVICE.toString() -> {
                    if (isVpnRunning) {
                        isVpnRunning = false
                        stopVpn()
                    }
                }

                Actions.START_VPN_SERVICE.toString() -> {
                    if (!isVpnRunning) {
                        startForeground(NOTIFICATION_ID, createNotification())
                        startVpn()
                    }
                }
            }
        }


        startForeground(NOTIFICATION_ID, createNotification())
        startVpn()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createNotification(): Notification {
        createNotificationChannel()

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("VPN Service")
            .setContentText("VPN service is running")
            .setSmallIcon(R.drawable.baseline_vpn_key_24)
            .setOngoing(true)
        addNotificationActions(builder)

        return builder.build()
    }

    private fun addNotificationActions(builder: NotificationCompat.Builder) {
        // Create an intent for stopping the VPN service
        val stopIntent = Intent(this, NormVpnService::class.java).apply {
            action = Actions.STOP_VPN_SERVICE.toString()
        }
        // Add FLAG_MUTABLE or FLAG_IMMUTABLE based on your use case
        val stopPendingIntent: PendingIntent =
            PendingIntent.getService(this, 0, stopIntent, PendingIntent.FLAG_IMMUTABLE)

        // Add the stop action to the notification
        builder.addAction(
            R.drawable.baseline_vpn_key_off_24,
            "Stop VPN",
            stopPendingIntent
        )
    }

    private fun createNotificationChannel() {
        val name = "Vpn Chanel"
        val descriptionText = "Vpn Service Channel"
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun startVpn() {
        vpnThread = Thread {
            try {
                val builder = Builder()
                vpnInterface = builder.setSession(getString(R.string.app_name))
                    .addAddress("10.0.0.1", 24)
                    .addDnsServer("8.8.8.8")
                    .addRoute("0.0.0.0", 0)
                    .setMtu(1500)
                    .establish()

                isVpnRunning = true

                val vpnInput = FileInputStream(vpnInterface?.fileDescriptor)
                val vpnOutput = FileInputStream(vpnInterface?.fileDescriptor)

                while (true) {

                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                stopVpn()
            }
        }
        vpnThread?.start()
    }

    private fun stopVpn() {
        try {
            if (isVpnRunning) {
                isVpnRunning = false
                vpnThread?.interrupt()
                vpnThread = null
                vpnInterface?.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopVpn()
    }

    companion object {
        var isVpnRunning = false
    }

    enum class Actions {
        STOP_VPN_SERVICE, START_VPN_SERVICE
    }
}