package com.ilkom.vviped.model

import com.google.gson.annotations.SerializedName

data class DetectedItem(
    @SerializedName("object_name")
    val objectName: String? = null
)