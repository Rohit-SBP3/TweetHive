package com.example.tweethive.api

import com.example.tweethive.model.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetHiveAPI {

    @GET("/v3/b/6749791facd3cb34a8b0ef60?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<TweetListItem>>

    @GET("/v3/b/6749791facd3cb34a8b0ef60?meta=false")
    @Headers("X-JSON-Path: \$.tweets[*].category")
    suspend fun getCategories(): Response<List<String>>

}