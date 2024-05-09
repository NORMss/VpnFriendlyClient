package com.norm.vpnfriendlyclient.domain.usecases

import com.norm.vpnfriendlyclient.domain.model.VpnKey
import com.norm.vpnfriendlyclient.domain.repository.VpnRepository

class UpsertServer(
    private val vpnRepository: VpnRepository,
) {
    suspend operator fun invoke(vpnKey: VpnKey) {
        vpnRepository.upsertServer(vpnKey)
    }
}