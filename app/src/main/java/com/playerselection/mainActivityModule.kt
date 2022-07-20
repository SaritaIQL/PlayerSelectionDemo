package com.playerselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.playerselection.ApiResponse.PlayerListResponse
import com.playerselection.ApiResponse.RulesResponse.RulesResponse
import com.playerselection.Appbase.BaseActivity
import com.playerselection.Injection.UserRepo
import com.playerselection.Injection.UserRepository
import com.playerselection.common.RequestState

class mainActivityModule(private val mUserRepository: UserRepo) : ViewModel() {

    //PLAYER_LIST
    private val playerListRepo = MutableLiveData<RequestState<PlayerListResponse>>()
    fun getPlayerList(): LiveData<RequestState<PlayerListResponse>> =
        playerListRepo

    fun getPlayerList(
        isInternetConnected: Boolean,
        baseView: BaseActivity,
    ) {
        val body = JsonObject()
        mUserRepository.getPlayerList(
            body,
            isInternetConnected,
            baseView,
            playerListRepo
        )
    }

    //PLAYER MAX MIN INFO
    private val playerRulesRepo = MutableLiveData<RequestState<RulesResponse>>()

    fun getPlayerRules(): LiveData<RequestState<RulesResponse>> =
        playerRulesRepo

    fun getPlayerMAX_MIN_INFO(
        isInternetConnected: Boolean,
        baseView: BaseActivity,
    ) {
        val body = JsonObject()
        mUserRepository.getPlayerRules(
            body,
            isInternetConnected,
            baseView,
            playerRulesRepo
        )
    }
}