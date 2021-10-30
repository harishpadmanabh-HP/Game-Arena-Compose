package com.example.gamearenacompose.di

import com.example.gamearenacompose.data.remote.RAWGApis
import com.example.gamearenacompose.data.remote.interceptors.ApiKeyInterceptor
import com.example.gamearenacompose.data.repositoy.GameRepository
import com.example.gamearenacompose.utils.RAWG_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) //single instance app life scope
object AppModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideApiKeyInterceptor() = ApiKeyInterceptor()


    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(RAWG_BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): RAWGApis = retrofit.create(RAWGApis::class.java)

    @Singleton
    @Provides
    fun provideGameRepository(rawgApis:RAWGApis) = GameRepository(api = rawgApis)


}