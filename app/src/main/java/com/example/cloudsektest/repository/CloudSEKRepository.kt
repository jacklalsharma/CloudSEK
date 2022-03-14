package com.example.cloudsektest.repository

import com.example.cloudsektest.data.model.AllAssetsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface CloudSEKRepository {
    fun getAllAssetsForDomain(domain: String): Flow<Response<AllAssetsResponse>>

}