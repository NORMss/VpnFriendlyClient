package com.norm.vpnfriendlyclient.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.norm.vpnfriendlyclient.data.AndroidVpnController
import com.norm.vpnfriendlyclient.data.lcoal.VpnDao
import com.norm.vpnfriendlyclient.data.lcoal.VpnDatabase
import com.norm.vpnfriendlyclient.data.repository.VpnRepositoryImpl
import com.norm.vpnfriendlyclient.domain.VpnController
import com.norm.vpnfriendlyclient.domain.repository.VpnRepository
import com.norm.vpnfriendlyclient.util.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
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
    ): VpnRepository = VpnRepositoryImpl(newsDao)
}