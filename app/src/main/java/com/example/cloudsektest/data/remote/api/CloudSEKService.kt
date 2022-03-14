package com.example.cloudsektest.data.remote.api

import com.example.cloudsektest.data.model.AllAssetsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface CloudSEKService {

    @GET("api/{domain_name}/all-assets/")
    suspend fun getAssetsForDomain(@Path("domain_name") domain: String): Response<AllAssetsResponse>

}