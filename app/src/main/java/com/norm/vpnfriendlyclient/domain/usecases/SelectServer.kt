package com.norm.vpnfriendlyclient.domain.usecases

import com.norm.vpnfriendlyclient.domain.model.VpnKey
import com.norm.vpnfriendlyclient.domain.repository.VpnRepository

class SelectServer(
    private val vpnRepository: VpnRepository,
) {
    suspend operator fun invoke(key: String): VpnKey? {
        return vpnRepository.selectServer(key)
    }
}