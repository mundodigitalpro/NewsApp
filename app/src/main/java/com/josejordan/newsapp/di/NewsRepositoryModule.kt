package com.josejordan.newsapp.di

import com.josejordan.newsapp.api.NewsAPI
import com.josejordan.newsapp.repository.NewsRepository
import com.josejordan.newsapp.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsRepositoryModule {

    @Provides
    fun provideNewsRepository(api: NewsAPI): NewsRepository = NewsRepositoryImpl(api)
}
