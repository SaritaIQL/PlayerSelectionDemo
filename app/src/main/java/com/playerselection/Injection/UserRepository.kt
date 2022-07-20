package com.playerselection.Injection

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.playerselection.ApiResponse.PlayerListResponse
import com.playerselection.ApiResponse.RulesResponse.RulesResponse
import com.playerselection.Appbase.BaseActivity
import com.playerselection.EndPoint.ApiEndPoint
import com.playerselection.common.ApiError
import com.playerselection.common.Config
import com.playerselection.common.NetworkManager
import com.playerselection.common.RequestState

class UserRepository(private val mApiEndPoint: ApiEndPoint) : UserRepo {
    override fun getPlayerList(
        body: JsonObject,
        internetConnected: Boolean,
        baseView: BaseActivity,
        lawyerResp: MutableLiveData<RequestState<PlayerListResponse>>
    ) {
        if (!internetConnected) {
            lawyerResp.value =
                RequestState(progress = false, error = ApiError(Config.NETWORK_ERROR, null))
        } else {
            lawyerResp.value = RequestState(progress = true)
            NetworkManager.requestData(
                mApiEndPoint.getPlayerList(),
                baseView,
                lawyerResp
            )
        }
    }

    override fun getPlayerRules(
        body: JsonObject,
        internetConnected: Boolean,
        baseView: BaseActivity,
        lawyerResp: MutableLiveData<RequestState<RulesResponse>>
    ) {
        if (!internetConnected) {
            lawyerResp.value =
                RequestState(progress = false, error = ApiError(Config.NETWORK_ERROR, null))
        } else {
            lawyerResp.value = RequestState(progress = true)
            NetworkManager.requestData(
                mApiEndPoint.getPlayerRules(),
                baseView,
                lawyerResp
            )
        }
    }
}