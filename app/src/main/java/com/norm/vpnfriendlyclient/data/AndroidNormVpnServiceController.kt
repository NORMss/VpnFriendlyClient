package com.norm.vpnfriendlyclient.data

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.net.VpnService
import android.os.IBinder
import android.util.Log
import com.norm.vpnfriendlyclient.domain.NormVpnServiceController
import com.norm.vpnfriendlyclient.domain.VpnKey
import com.norm.vpnfriendlyclient.service.NormVpnService
import com.norm.vpnfriendlyclient.util.PrepareVpnActivity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AndroidNormVpnServiceController(
    private val context: Context,
) : NormVpnServiceController {
    private var normVpnService: NormVpnService? = null
    private var isNormVpnServiceBound: Boolean = false

    private val boundServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder: NormVpnService.NormVpnBinder = service as NormVpnService.NormVpnBinder
            normVpnService = binder.getService()
            isNormVpnServiceBound = true
            Log.d("MyLog", "onServiceConnected")
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            normVpnService?.stopVpn()
            normVpnService = null
            isNormVpnServiceBound = false
            Log.d("MyLog", "onServiceDisconnected")
        }
    }

    private fun bindToNormVpnService() {
        Intent(context, NormVpnService::class.java).also {
            context.bindService(it, boundServiceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    private val _isVpnRunning = MutableStateFlow(false)
    override val isVpnRunning: StateFlow<Boolean>
        get() = _isVpnRunning.asStateFlow()

    private val normVpnStateReceiver = NormVpnStateReceiver {
        _isVpnRunning.update { it }
        Log.d("MyLog", it.toString())
    }

    init {
        Intent(context, PrepareVpnActivity::class.java).also {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(it)
        }
        if (VpnService.prepare(context) == null) {
            bindToNormVpnService()
            context.registerReceiver(
                normVpnStateReceiver,
                IntentFilter(NormVpnService.Actions.IS_VPN_RUNNING.toString()),
                Context.RECEIVER_EXPORTED,
            )
        }
    }

    private val _listKeys = MutableStateFlow<List<VpnKey>>(emptyList())
    override val listKeys: StateFlow<List<VpnKey>>
        get() = _listKeys.asStateFlow()

    private val _errors = MutableSharedFlow<String>()
    override val errors: SharedFlow<String>
        get() = _errors.asSharedFlow()

    override fun startVpn() {
        normVpnService?.startVpn()
    }

    override fun stopVpn() {
        normVpnService?.stopVpn()
    }

    fun getStateVpnRunning(): Boolean {
        val status = normVpnService?.getStateVpnRunning() ?: false
        Log.d("MyLog", "$status")
        return normVpnService?.getStateVpnRunning() ?: false
    }
}