package com.ofekte.gurushotstest.photos_feature.domain.usecases

import com.ofekte.gurushotstest.common.Resource
import com.ofekte.gurushotstest.data.repositories.PhotosRepository
import com.ofekte.gurushotstest.photos_feature.domain.mapper.toPhoto
import com.ofekte.gurushotstest.photos_feature.domain.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.concurrent.CancellationException
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photosRepository: PhotosRepository
) {

    suspend operator fun invoke(
        memberId: String,
        start: Int,
        limit: Int
    ): Flow<Resource<List<Photo>>> = flow {
        emit(Resource.Loading())
        try {
            val photos = photosRepository
                .getPhotos(memberId, start, limit)
                .map { it.toPhoto() }

            emit(Resource.Success(photos))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            emit(Resource.Error(""))
        }
    }
}