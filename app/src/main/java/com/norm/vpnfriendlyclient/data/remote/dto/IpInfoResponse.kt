package com.norm.vpnfriendlyclient.data.remote.dto

data class IpInfoResponse(
    val ip: String,
    val city: String,
    val region: String,
    val country: String,
    val loc: String,
    val org: String,
    val postal: String,
    val timezone: String
)