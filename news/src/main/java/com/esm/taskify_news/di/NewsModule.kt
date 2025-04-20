package com.esm.taskify_news.di

import android.app.Application
import androidx.room.Room
import com.esm.taskify_news.data.local.NewsDao
import com.esm.taskify_news.data.local.NewsDatabase
import com.esm.taskify_news.data.local.NewsTypeConvertor
import com.esm.taskify_news.data.manager.LocalUserManagerImpl
import com.esm.taskify_news.data.remote.NewsApi
import com.esm.taskify_news.data.repository.NewsRepositoryImpl
import com.esm.taskify_news.domain.manager.LocalUserManager
import com.esm.taskify_news.domain.model.NewsRepository
import com.esm.taskify_news.domain.usecases.app_entry.AppEntryUseCases
import com.esm.taskify_news.domain.usecases.app_entry.ReadAppEntry
import com.esm.taskify_news.domain.usecases.app_entry.SaveAppEntry
import com.esm.taskify_news.domain.usecases.news.DeleteArticle
import com.esm.taskify_news.domain.usecases.news.GetArticle
import com.esm.taskify_news.domain.usecases.news.GetArticles
import com.esm.taskify_news.domain.usecases.news.GetNews
import com.esm.taskify_news.domain.usecases.news.NewsUseCases
import com.esm.taskify_news.domain.usecases.news.SearchNews
import com.esm.taskify_news.domain.usecases.news.UpsertArticle
import com.esm.taskify_news.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
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


    @Provides
    @Singleton
    fun provideApiInstance(): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            getArticles = GetArticles(newsDao),
            getArticle = GetArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "news_db"
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}