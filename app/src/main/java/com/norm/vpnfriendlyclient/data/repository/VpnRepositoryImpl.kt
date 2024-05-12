package com.norm.vpnfriendlyclient.data.repository

import android.util.Log
import com.norm.vpnfriendlyclient.data.lcoal.VpnDao
import com.norm.vpnfriendlyclient.data.remote.IpInfoApi
import com.norm.vpnfriendlyclient.data.remote.IpInfoLocation
import com.norm.vpnfriendlyclient.domain.model.IpLocation
import com.norm.vpnfriendlyclient.domain.model.VpnKey
import com.norm.vpnfriendlyclient.domain.repository.VpnRepository
import kotlinx.coroutines.flow.Flow

class VpnRepositoryImpl(
    private val vpnDao: VpnDao,
    private val ipInfoApi: IpInfoApi,
) : VpnRepository {
    override suspend fun upsertServer(vpnKey: VpnKey) {
        vpnDao.upsert(vpnKey)
    }

    override suspend fun deleteServer(vpnKey: VpnKey) {
        vpnDao.delete(vpnKey)
    }

    override fun selectedServers(): Flow<List<VpnKey>> {
        return vpnDao.getServers()
    }

    override suspend fun selectServer(key: String): VpnKey? {
        return vpnDao.getServer(key)
    }

    override suspend fun getIpLocation(ip: String): IpLocation {
        Log.d("MyLogIp", ip)
        return IpInfoLocation(
            ipInfoApi = ipInfoApi,
            ip = ip,
        ).getIpLocation()
    }
}