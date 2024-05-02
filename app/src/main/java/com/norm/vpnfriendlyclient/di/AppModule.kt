package com.norm.vpnfriendlyclient.di

import android.content.Context
import com.norm.vpnfriendlyclient.data.AndroidVpnController
import com.norm.vpnfriendlyclient.domain.VpnController
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
}