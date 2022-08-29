package com.ofekte.gurushotstest.data.dto

import com.google.gson.annotations.SerializedName

data class GetPhotosResponse(
    @SerializedName("items") val photos: List<PhotoDto>,
    val success: Boolean
)