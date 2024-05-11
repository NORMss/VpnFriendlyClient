package com.norm.vpnfriendlyclient.data.remote

import com.norm.vpnfriendlyclient.data.remote.dto.IpInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IpInfoApi {
    @GET("{ip}")
    suspend fun getIpInfo(
        @Path("ip") ip: String,
    ): IpInfoResponse
}