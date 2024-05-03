package com.norm.vpnfriendlyclient.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.VpnService
import android.os.Binder
import android.os.IBinder
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.core.app.NotificationCompat
import com.norm.vpnfriendlyclient.R
import com.norm.vpnfriendlyclient.util.CHANNEL_ID
import com.norm.vpnfriendlyclient.util.NOTIFICATION_ID
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.DatagramSocket
import java.nio.ByteBuffer

class NormVpnService : VpnService() {
    private val binder by lazy { NormVpnBinder() }

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
        sendStateVpnRunning()
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

    fun startVpn() {
        Log.d("MyLog", "startVpn")
        vpnThread = Thread {
            try {
                establishVpnConnection()
                isVpnRunning = true

                while (true) {
                    // Read data from the VPN interface
                    val input = FileInputStream(vpnInterface?.fileDescriptor)
                    val buffer = ByteBuffer.allocate(4096)
                    val length = input.read(buffer.array())
                    if (length > 0) {
                        // Process the received data

                        // Write data back to the VPN interface (echo)
                        val output = FileOutputStream(vpnInterface?.fileDescriptor)
                        output.write(buffer.array(), 0, length)
                        output.close()
                    }
                    input.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                stopVpn()
            }
        }
        vpnThread?.start()
        Log.d("MyLog", "startVpn()")
        sendStateVpnRunning()
        startForeground(NOTIFICATION_ID, createNotification())
    }

    private fun establishVpnConnection() {
        val builder = Builder()
        vpnInterface = builder.setSession("NormVpnService")
            .addAddress("10.0.0.2", 32)
            .addRoute("0.0.0.0", 0)
            .establish()

        isVpnRunning = true
    }

    fun stopVpn() {
        Log.d("MyLog", "stopVpn()")
        if (isVpnRunning) {
            isVpnRunning = false
            vpnThread?.interrupt()
            vpnThread = null
            vpnInterface?.close()
            isVpnRunning = false
            sendStateVpnRunning()
            stopSelf()
        }
    }

    override fun onRevoke() {
        super.onRevoke()
        DatagramSocket().close()
        ParcelFileDescriptor(vpnInterface).close()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopVpn()
    }

    private fun sendStateVpnRunning() {
        Intent(Actions.IS_VPN_RUNNING.toString()).also {
            it.putExtra("isvpnrunning", isVpnRunning)
            sendBroadcast(it)
        }
        Log.d("MyLog", "Service: $isVpnRunning")
    }

    fun getStateVpnRunning(): Boolean {
        return isVpnRunning
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    companion object {
        var isVpnRunning = false
    }

    enum class Actions {
        STOP_VPN_SERVICE, START_VPN_SERVICE, IS_VPN_RUNNING
    }

    inner class NormVpnBinder : Binder() {
        fun getService(): NormVpnService = this@NormVpnService
    }
}