package com.norm.vpnfriendlyclient.data.lcoal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.norm.vpnfriendlyclient.domain.model.VpnKey
import kotlinx.coroutines.flow.Flow

@Dao
interface VpnDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vpnKey: VpnKey)

    @Delete
    suspend fun delete(vpnKey: VpnKey)

    @Query("SELECT * FROM servers")
    fun getServers(): Flow<List<VpnKey>>

    @Query("SELECT * FROM servers WHERE 'key'=:key")
    suspend fun getServer(key: String): VpnKey?
}