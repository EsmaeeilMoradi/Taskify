package com.esm.taskify_news.di

import android.app.Application
import com.esm.taskify_news.data.manager.LocalUserManagerImpl
import com.esm.taskify_news.domain.manager.LocalUserManager
import com.esm.taskify_news.domain.usecases.app_entry.AppEntryUseCases
import com.esm.taskify_news.domain.usecases.app_entry.ReadAppEntry
import com.esm.taskify_news.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

}