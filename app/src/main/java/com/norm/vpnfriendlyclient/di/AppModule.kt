package com.norm.vpnfriendlyclient.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.norm.vpnfriendlyclient.data.AndroidVpnController
import com.norm.vpnfriendlyclient.data.lcoal.VpnDao
import com.norm.vpnfriendlyclient.data.lcoal.VpnDatabase
import com.norm.vpnfriendlyclient.data.remote.IpInfoApi
import com.norm.vpnfriendlyclient.data.repository.VpnRepositoryImpl
import com.norm.vpnfriendlyclient.domain.VpnController
import com.norm.vpnfriendlyclient.domain.repository.VpnRepository
import com.norm.vpnfriendlyclient.domain.usecases.DeleteServer
import com.norm.vpnfriendlyclient.domain.usecases.GetIpLocation
import com.norm.vpnfriendlyclient.domain.usecases.SelectServer
import com.norm.vpnfriendlyclient.domain.usecases.SelectServers
import com.norm.vpnfriendlyclient.domain.usecases.ServerUseCases
import com.norm.vpnfriendlyclient.domain.usecases.UpsertServer
import com.norm.vpnfriendlyclient.util.BASE_URL
import com.norm.vpnfriendlyclient.util.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideIpInfoApp(): IpInfoApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IpInfoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideVpnController(@ApplicationContext context: Context): VpnController {
        return AndroidVpnController(context)
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        vpnDatabase: VpnDatabase
    ): VpnDao = vpnDatabase.newDao

    @Provides
    @Singleton
    fun provideVpnDatabase(
        application: Application,
    ): VpnDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = VpnDatabase::class.java,
            name = DB_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsDao: VpnDao,
        ipInfoApi: IpInfoApi,
    ): VpnRepository = VpnRepositoryImpl(
        newsDao,
        ipInfoApi,
    )

    @Provides
    @Singleton
    fun provideServerUseCases(
        vpnRepository: VpnRepository,
    ): ServerUseCases {
        return ServerUseCases(
            deleteServer = DeleteServer(vpnRepository),
            selectServer = SelectServer(vpnRepository),
            selectServers = SelectServers(vpnRepository),
            upsertServer = UpsertServer(vpnRepository),
            getIpLocation = GetIpLocation(vpnRepository),
        )
    }
}