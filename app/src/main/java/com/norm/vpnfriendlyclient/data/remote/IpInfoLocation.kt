package com.norm.vpnfriendlyclient.data.remote

import com.norm.vpnfriendlyclient.domain.model.IpLocation

class IpInfoLocation(
    private val ipInfoApi: IpInfoApi,
    private val ip: String,
) {
    suspend fun getIpLocation(): IpLocation {
        return try {
            val ipInfoResponse = ipInfoApi.getIpInfo(
                ip = ip
            )
            IpLocation(
                city = ipInfoResponse.city,
                country = ipInfoResponse.country,
                loc = ipInfoResponse.loc,
                org = ipInfoResponse.org,
                region = ipInfoResponse.region,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            IpLocation(
                city = "undefined",
                country = "undefined",
                loc = "undefined",
                org = "undefined",
                region = "undefined",
            )
        }
    }
}