package com.norm.vpnfriendlyclient.data

import android.content.Context
import android.content.Intent
import com.norm.vpnfriendlyclient.domain.NormVpnServiceController
import com.norm.vpnfriendlyclient.domain.VpnKey
import com.norm.vpnfriendlyclient.service.NormVpnService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class AndroidNormVpnServiceController(
    private val context: Context,
) : NormVpnServiceController {

    private val _isVpnRunning = MutableStateFlow<Boolean>(false)
    override val isVpnRunning: StateFlow<Boolean>
        get() = _isVpnRunning.asStateFlow()

    private val _listKeys = MutableStateFlow<List<VpnKey>>(emptyList())
    override val listKeys: StateFlow<List<VpnKey>>
        get() = _listKeys.asStateFlow()

    private val _errors = MutableSharedFlow<String>()
    override val errors: SharedFlow<String>
        get() = _errors.asSharedFlow()

    override fun startVpn() {
        Intent(context, NormVpnService::class.java).also {
            it.action = NormVpnService.Actions.START_VPN_SERVICE.toString()
        }
    }

    override fun stopVpn() {
        Intent(context, NormVpnService::class.java).also {
            it.action = NormVpnService.Actions.STOP_VPN_SERVICE.toString()
        }
    }

    private fun getStateRunningVpn() {
    }
}