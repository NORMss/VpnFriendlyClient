package com.norm.vpnfriendlyclient.domain.repository

import com.norm.vpnfriendlyclient.domain.model.VpnKey
import kotlinx.coroutines.flow.Flow

interface VpnRepository {
    suspend fun upsertServer(vpnKey: VpnKey)

    suspend fun deleteServer(vpnKey: VpnKey)

    fun selectedServers(): Flow<List<VpnKey>>

    suspend fun selectServer(key: String): VpnKey?
}