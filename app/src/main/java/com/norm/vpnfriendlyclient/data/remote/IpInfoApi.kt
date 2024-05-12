package com.norm.vpnfriendlyclient.data.remote

import com.norm.vpnfriendlyclient.data.remote.dto.IpInfoResponse
import com.norm.vpnfriendlyclient.util.IPINFO_TOKEN
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IpInfoApi {
    @GET("{ip}")
    suspend fun getIpInfo(
        @Path("ip") ip: String,
        @Query("token") token: String = IPINFO_TOKEN,
    ): IpInfoResponse
}