package com.example.cloudsektest.hilt

import android.content.Context
import com.example.cloudsektest.data.remote.api.CloudSEKService
import com.example.cloudsektest.data.remote.interceptor.HeaderInterceptor
import com.example.cloudsektest.utils.Network
import com.example.cloudsektest.utils.NetworkConnectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    private val retrofitBuilder: Retrofit.Builder =
        Retrofit.Builder().baseUrl("http://osint.bevigil.com/")
            .addConverterFactory(GsonConverterFactory.create())

    private val okHttpBuilder: OkHttpClient.Builder =
        OkHttpClient.Builder().readTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES)

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    @Provides
    fun provideCloudSEKService(
        headerInterceptor: HeaderInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): CloudSEKService {
        return retrofitBuilder.client(
            okHttpBuilder.addInterceptor(httpLoggingInterceptor)
                .addInterceptor(headerInterceptor).build()
        )
            .build().create(CloudSEKService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivity {
        return Network(context)
    }

}