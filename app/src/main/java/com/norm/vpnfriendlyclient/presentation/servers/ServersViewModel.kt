package com.norm.vpnfriendlyclient.presentation.servers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.norm.vpnfriendlyclient.domain.model.VpnKey
import com.norm.vpnfriendlyclient.domain.usecases.ServerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServersViewModel @Inject constructor(
    private val serverUseCases: ServerUseCases,
) : ViewModel() {
    private val _state = mutableStateOf(ServersState())
    val state: State<ServersState> = _state

    init {
        getServers()
    }

    private fun getServers() {
        serverUseCases.selectServers().onEach {
            _state.value = _state.value.copy(servers = it.asReversed())
        }.launchIn(viewModelScope)
    }

    fun editServer(vpnKey: VpnKey) {
        viewModelScope.launch {
            serverUseCases.upsertServer(vpnKey)
        }
    }

    fun deleteServer(vpnKey: VpnKey) {
        viewModelScope.launch {
            serverUseCases.deleteServer(vpnKey)
        }
    }

    fun addServer(vpnKey: VpnKey) {
        val ip = parseIPv4Address(vpnKey)
        viewModelScope.launch {
            val vpnKeyUpsert = vpnKey.copy(
                country = ip?.let {
                    getCountry(ip)
                } ?: "undefined",
                name = vpnKey.name?.let {
                    vpnKey.name
                } ?: "Server #${_state.value.servers.size + 1}"
            )
            serverUseCases.upsertServer(vpnKeyUpsert)
        }
    }

    private suspend fun getCountry(ip: String) = serverUseCases.getIpLocation(ip).country

    private fun parseIPv4Address(vpnKey: VpnKey) = vpnKey.key.let {
        Regex("""(?<=@)\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}""").find(it)?.value
    }
}