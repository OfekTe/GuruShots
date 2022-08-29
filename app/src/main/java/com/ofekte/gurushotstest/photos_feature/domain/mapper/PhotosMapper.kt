package com.ofekte.gurushotstest.photos_feature.domain.mapper

import com.ofekte.gurushotstest.common.BASE_PHOTOS_URL
import com.ofekte.gurushotstest.data.dto.PhotoDto
import com.ofekte.gurushotstest.photos_feature.domain.model.Photo

fun PhotoDto.toPhoto() = Photo(
    likes = likes,
    votes = votes,
    views = views,
    hasLiked = hasLiked,
    squareImageUrl = BASE_PHOTOS_URL + "250x250/$memberId/3_$id.jpg",
    wideImageUrl = BASE_PHOTOS_URL + "0x250/$memberId/3_$id.jpg"
)