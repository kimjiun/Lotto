package com.jiun.lotto.di

import com.jiun.lotto.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton
import timber.log.Timber

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://www.dhlottery.co.kr/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp_ALL").d(message)
                if ((!message.startsWith("{") && !message.startsWith("["))) {
                    if(message.contains("https://") || message.contains("http://"))
                        Timber.tag("OkHttp_REQ").d(message)
                    return
                }

                try { // Timber 와 Gson setPrettyPrinting 를 이용해 json 을 보기 편하게 표시해준다.
                    Timber.tag("Response").d(message)
                } catch (m: SerializationException) {
                    Timber.tag("Response error").e(m)
                }
            }
        }).apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
