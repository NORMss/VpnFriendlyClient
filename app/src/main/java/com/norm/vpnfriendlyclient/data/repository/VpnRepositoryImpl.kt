package com.norm.vpnfriendlyclient.data.repository

import com.norm.vpnfriendlyclient.data.lcoal.VpnDao
import com.norm.vpnfriendlyclient.domain.model.VpnKey
import com.norm.vpnfriendlyclient.domain.repository.VpnRepository
import kotlinx.coroutines.flow.Flow

class VpnRepositoryImpl(
    private val vpnDao: VpnDao,
) : VpnRepository {
    override suspend fun upsertServer(vpnKey: VpnKey) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteServer(vpnKey: VpnKey) {
        TODO("Not yet implemented")
    }

    override fun selectedServers(): Flow<List<VpnKey>> {
        TODO("Not yet implemented")
    }

    override suspend fun selectServer(key: String): VpnKey? {
        TODO("Not yet implemented")
    }
}