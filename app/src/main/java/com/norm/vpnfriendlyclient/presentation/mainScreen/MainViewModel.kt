package com.norm.vpnfriendlyclient.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.norm.vpnfriendlyclient.domain.VpnController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val vpnController: VpnController,
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)


    fun startVpn() {
        vpnController.startVpn()
    }

    fun stopVpn() {
        vpnController.stopVpn()
    }
}