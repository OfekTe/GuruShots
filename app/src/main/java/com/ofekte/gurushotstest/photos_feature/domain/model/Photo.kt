package com.ofekte.gurushotstest.photos_feature.domain.model

data class Photo(
    val likes: Int,
    val votes: Int,
    val views: Int,
    val hasLiked: Boolean,
    val squareImageUrl: String,
    val wideImageUrl: String,
)