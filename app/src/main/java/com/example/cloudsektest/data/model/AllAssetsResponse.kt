package com.example.cloudsektest.data.model

import com.google.gson.annotations.SerializedName

data class AllAssetsResponse(
    @SerializedName("package_id") var packageId: String? = null,
    @SerializedName("assets") var assets: Assets? = Assets()
)

data class Assets(
    @SerializedName("url") var url: ArrayList<String> = arrayListOf(),
    @SerializedName("file_path") var filePath: ArrayList<String> = arrayListOf(),
    @SerializedName("host") var host: ArrayList<String> = arrayListOf(),
    @SerializedName("filename") var filename: ArrayList<String> = arrayListOf(),
    @SerializedName("rest_api") var restApi: ArrayList<String> = arrayListOf(),
    @SerializedName("ip_url") var ipUrl: ArrayList<String> = arrayListOf(),
    @SerializedName("relative_endpoint") var relativeEndpoint: ArrayList<String> = arrayListOf(),
    @SerializedName("IP Address disclosure") var ipAddressDisclosure: ArrayList<String> = arrayListOf(),
    @SerializedName("AWS URL") var awsURL: ArrayList<String> = arrayListOf(),
    @SerializedName("email") var email: ArrayList<String> = arrayListOf(),
    @SerializedName("Firebase URL") var firebaseURL: ArrayList<String> = arrayListOf()
)

data class AssetsUi(val name: String, val values: List<String>)

fun Assets.toAssetsUi(): List<AssetsUi> {
    val list: MutableList<AssetsUi> = mutableListOf()
    list.add(AssetsUi("Url", url))
    list.add(AssetsUi("File Path", filePath))
    list.add(AssetsUi("Host", host))
    list.add(AssetsUi("File Name", filename))
    list.add(AssetsUi("Rest Api", restApi))
    list.add(AssetsUi("IP Url", ipUrl))
    list.add(AssetsUi("Relative Endpoint", relativeEndpoint))
    list.add(AssetsUi("IP Address Disclosure", ipAddressDisclosure))
    list.add(AssetsUi("AWS Url", awsURL))
    list.add(AssetsUi("Email", email))
    list.add(AssetsUi("Firebase Url", firebaseURL))
    return list
}