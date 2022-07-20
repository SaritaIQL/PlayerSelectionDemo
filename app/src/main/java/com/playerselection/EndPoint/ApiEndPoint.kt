package com.playerselection.EndPoint

import com.playerselection.ApiResponse.PlayerListResponse
import com.playerselection.ApiResponse.RulesResponse.RulesResponse
import com.playerselection.common.CommonResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("getAllPlayer")
    fun getPlayerList(): Call<CommonResponseModel<PlayerListResponse>>

    @GET("getAllPlayerRules")
    fun getPlayerRules(): Call<CommonResponseModel<RulesResponse>>
}