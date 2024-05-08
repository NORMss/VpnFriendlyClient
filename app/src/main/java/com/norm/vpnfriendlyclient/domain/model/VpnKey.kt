package com.norm.vpnfriendlyclient.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servers")
data class VpnKey(
    @PrimaryKey val key: String,
    val name: String?,
    val country: String?,
)
