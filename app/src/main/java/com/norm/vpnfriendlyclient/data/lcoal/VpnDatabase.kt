package com.norm.vpnfriendlyclient.data.lcoal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.norm.vpnfriendlyclient.domain.model.VpnKey

@Database(
    entities = [VpnKey::class],
    version = 1,
)

abstract class VpnDatabase : RoomDatabase() {
    abstract val newDao: VpnDao
}