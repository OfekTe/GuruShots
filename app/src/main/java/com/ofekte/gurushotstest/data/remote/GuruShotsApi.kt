package com.ofekte.gurushotstest.data.remote

import com.ofekte.gurushotstest.data.dto.GetPhotosResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GuruShotsApi {

    @FormUrlEncoded
    @POST("get_photos_public")
    suspend fun getPhotos(
        @Field("member_id") memberId: String,
        @Field("get_likes") withLikes: Boolean = true,
        @Field("get_votes") withVotes: Boolean = true,
        @Field("get_liked") withMyLike: Boolean = true,
        @Field("start") start: Int,
        @Field("limit") limit: Int,
    ): GetPhotosResponse
}