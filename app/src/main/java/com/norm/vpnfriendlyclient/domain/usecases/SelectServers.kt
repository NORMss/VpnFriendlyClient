package com.norm.vpnfriendlyclient.domain.usecases

import com.norm.vpnfriendlyclient.domain.model.VpnKey
import com.norm.vpnfriendlyclient.domain.repository.VpnRepository
import kotlinx.coroutines.flow.Flow

class SelectServers(
    private val vpnRepository: VpnRepository,
) {
    operator fun invoke(): Flow<List<VpnKey>> {
        return vpnRepository.selectedServers()
    }
}