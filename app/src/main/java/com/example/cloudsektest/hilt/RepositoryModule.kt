package com.example.cloudsektest.hilt

import com.example.cloudsektest.repository.CloudSEKRepository
import com.example.cloudsektest.repository.impl.CloudSEKRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideCloudSEKRepository(cloudSEKRepositoryImpl: CloudSEKRepositoryImpl): CloudSEKRepository
}