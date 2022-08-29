package com.ofekte.gurushotstest.data.repositories

import com.ofekte.gurushotstest.data.dto.PhotoDto
import com.ofekte.gurushotstest.data.remote.GuruShotsApi
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val guruShotsApi: GuruShotsApi
) : PhotosRepository {

    override suspend fun getPhotos(
        memberId: String,
        start: Int,
        limit: Int
    ): List<PhotoDto> {
        val response = guruShotsApi.getPhotos(
            memberId = memberId,
            start = start,
            limit = limit
        )
        return response.photos
    }
}