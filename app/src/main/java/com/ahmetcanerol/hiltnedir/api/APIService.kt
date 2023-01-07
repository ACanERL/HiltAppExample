package com.ahmetcanerol.hiltnedir.api

import com.ahmetcanerol.hiltnedir.model.Root
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("assets")
    suspend fun getCoins(
        @Query("apikey")apikey:String,
        @Query("limit")limit:Int
    ):Response<Root>
}