package com.norm.vpnfriendlyclient.domain.usecases

import com.norm.vpnfriendlyclient.domain.model.IpLocation
import com.norm.vpnfriendlyclient.domain.repository.VpnRepository

class GetIpLocation(
    private val vpnRepository: VpnRepository,
) {
    suspend operator fun invoke(ip: String): IpLocation {
        return vpnRepository.getIpLocation(ip)
    }
}