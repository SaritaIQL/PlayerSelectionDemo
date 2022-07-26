package com.playerselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.playerselection.ApiResponse.PlayerListResponse
import com.playerselection.ApiResponse.RulesResponse.Max_min
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

     val allRoounderSize = MutableLiveData<Max_min>()
     val wicketkeeper = MutableLiveData<Max_min>()
     val batsman = MutableLiveData<Max_min>()
     val bowler = MutableLiveData<Max_min>()

    val inputSelection = MutableLiveData<String>()

    init {
        inputSelection.value="No selection"
    }
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

    fun getSelectionPlayerRange(selectionCategory: String) : String {
        when(selectionCategory){
            "A"->{
                inputSelection.value="Selection allrounder( "+ allRoounderSize.value!!.min + " - " + allRoounderSize.value!!.max.toString()+ " )"
            }
            "W"->{
                inputSelection.value="Selection wicketkeepr( "+ wicketkeeper.value!!.min + " - " + wicketkeeper.value!!.max.toString()+ " )"

            }
            "BL"->{
                inputSelection.value="Selection bolwer( "+ bowler.value!!.min + " - " + bowler.value!!.max.toString()+ " )"
            }
            "BT"->{
                inputSelection.value="Selection batman( "+ batsman.value!!.min + " - " + batsman.value!!.max.toString()+ " )"
            }
            else->{

            }
        }
        return  inputSelection.value!!

    }
}