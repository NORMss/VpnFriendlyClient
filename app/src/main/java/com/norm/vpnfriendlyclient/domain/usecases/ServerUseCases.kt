package com.norm.vpnfriendlyclient.domain.usecases

data class ServerUseCases(
    val deleteServer: DeleteServer,
    val selectServer: SelectServer,
    val selectServers: SelectServers,
    val upsertServer: UpsertServer,
    val getIpLocation: GetIpLocation,
)