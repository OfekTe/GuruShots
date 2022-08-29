package com.ofekte.gurushotstest.data.dto

import com.google.gson.annotations.SerializedName

data class PhotoDto (
    val id: String,
    val ratio: Float,
    @SerializedName("member_id") val memberId: String,
    val title: String,
    val width: Int,
    val height: Int,
    val views: Int,
    @SerializedName("adult") val isAdult: Boolean,
    val uploadDate: Long,
    val labels: List<String> = listOf(),
    val eventId: String,
    val likes: Int,
    val votes: Int,
    @SerializedName("liked") val hasLiked: Boolean
)