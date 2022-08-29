package com.ofekte.gurushotstest.di

import com.ofekte.gurushotstest.data.repositories.PhotosRepository
import com.ofekte.gurushotstest.data.repositories.PhotosRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PhotosRepositoryModule {

    @Binds
    abstract fun bindPhotosRepository(
        photosRepositoryImpl: PhotosRepositoryImpl
    ): PhotosRepository
}