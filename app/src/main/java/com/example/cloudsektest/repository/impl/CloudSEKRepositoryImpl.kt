package com.example.cloudsektest.repository.impl

import com.example.cloudsektest.data.model.AllAssetsResponse
import com.example.cloudsektest.data.remote.api.CloudSEKService
import com.example.cloudsektest.repository.CloudSEKRepository
import com.example.cloudsektest.repository.Response
import com.example.cloudsektest.utils.NetworkConnectivity
import com.example.cloudsektest.utils.getResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CloudSEKRepositoryImpl @Inject internal constructor(
    private val cloudSEKService: CloudSEKService,
    private val networkConnectivity: NetworkConnectivity
) : CloudSEKRepository {
    override fun getAllAssetsForDomain(domain: String): Flow<Response<AllAssetsResponse>> = flow {
        if (!networkConnectivity.isConnected()) {
            emit(Response.error("You are offline. Connect to the Internet to access the app"))
            return@flow
        } else {
            val response = cloudSEKService.getAssetsForDomain(domain).getResponse()
            val state = if (response != null) Response.success(response) else Response.error(
                "Something went wrong"
            )
            emit(state)
        }
    }.catch { e -> emit(Response.error("Something went wrong $e")) }

}