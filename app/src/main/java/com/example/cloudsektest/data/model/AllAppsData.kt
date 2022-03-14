package com.example.cloudsektest.data.model

import android.graphics.drawable.Drawable

data class AllAppsData(
    var packageId: String? = null,
    var appName: String? = null,
    var appVersion: String? = null,
    var icon: Drawable? = null
)