package com.playerselection.Injection

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.playerselection.ApiResponse.PlayerListResponse
import com.playerselection.ApiResponse.RulesResponse.RulesResponse
import com.playerselection.Appbase.BaseActivity
import com.playerselection.common.RequestState

interface UserRepo {
    fun getPlayerList(
        body: JsonObject,
        internetConnected: Boolean,
        baseView: BaseActivity,
        lawyerResp: MutableLiveData<RequestState<MutableList<PlayerListResponse>>>
    )

    fun getPlayerRules(
        body: JsonObject,
        internetConnected: Boolean,
        baseView: BaseActivity,
        lawyerResp: MutableLiveData<RequestState<MutableList<RulesResponse>>>
    )
}