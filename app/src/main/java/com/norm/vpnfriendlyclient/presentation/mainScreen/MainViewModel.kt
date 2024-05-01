package com.norm.vpnfriendlyclient.presentation.mainScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    fun startVpn() {

    }

    fun stopVpn() {

    }
}