package com.norm.vpnfriendlyclient.presentation.servers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.norm.vpnfriendlyclient.domain.usecases.ServerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
}