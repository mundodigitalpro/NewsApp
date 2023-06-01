package com.josejordan.newsapp.di

import com.josejordan.newsapp.utils.Constants
import com.josejordan.newsapp.api.NewsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NewsAPIModule {

    @Provides
    fun provideNewsAPI(): NewsAPI {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()

        return retrofit.create(NewsAPI::class.java)
    }
}
