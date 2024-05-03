package com.norm.vpnfriendlyclient.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.norm.vpnfriendlyclient.domain.VpnController
import com.norm.vpnfriendlyclient.domain.VpnRunResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val vpnController: VpnController,
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    init {
        vpnController.isVpnRunning.onEach { isVpnRunning ->
            _state.update {
                it.copy(isVpnRunning = isVpnRunning)
            }
        }.launchIn(viewModelScope)

        vpnController.errors.onEach { error ->
            _state.update {
                it.copy(
                    errorMessage = error
                )
            }
        }
    }

    fun startVpn() {
        vpnController.startVpn()
        _state.update {
            it.copy(
                isVpnRunning = true
            )
        }
    }

    fun stopVpn() {
        vpnController.stopVpn()
        _state.update {
            it.copy(
                isVpnRunning = false
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun Flow<VpnRunResult>.listen(): Job {
        return onEach { vpnRunResult ->
            when (vpnRunResult) {
                is VpnRunResult.VpnRunEstablished -> {
                    _state.update {
                        it.copy(
                            isVpnRunning = true
                        )
                    }
                }

                is VpnRunResult.Error -> {
                    _state.update {
                        it.copy(
                            isVpnRunning = false,
                            errorMessage = vpnRunResult.message
                        )
                    }
                }
            }
        }
            .catch {

            }
            .launchIn(viewModelScope)
    }
}