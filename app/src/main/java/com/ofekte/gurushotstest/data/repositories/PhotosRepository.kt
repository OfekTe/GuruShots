package com.ofekte.gurushotstest.data.repositories

import com.ofekte.gurushotstest.data.dto.PhotoDto

interface PhotosRepository {

    suspend fun getPhotos(
        memberId: String,
        start: Int,
        limit: Int
    ): List<PhotoDto>
}