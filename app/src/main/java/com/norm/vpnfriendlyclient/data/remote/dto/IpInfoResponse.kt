package com.norm.vpnfriendlyclient.data.remote.dto

data class IpInfoResponse(
    val city: String,
    val country: String,
    val hostname: String,
    val ip: String,
    val loc: String,
    val org: String,
    val postal: String,
    val region: String,
    val timezone: String
)