package com.mobcomp.vviped.model

data class UploadResponse (
    val error: Boolean,
    val message: String,
    val image: String
)