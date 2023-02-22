package com.dschumerth.api.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {
    @GET("c")
    suspend fun getImage(): ResponseBody

    @GET("cat/gif")
    suspend fun getGif(): ResponseBody

    @GET("cat/")
    suspend fun getCat(
        @Query("id") id: Int
    ): ResponseBody

    @GET("api/cats?tags=tag1,tag2&skip=0&limit=10")
    suspend fun getCats(): ResponseBody
}
