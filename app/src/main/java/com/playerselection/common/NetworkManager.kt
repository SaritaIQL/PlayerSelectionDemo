package com.playerselection.common

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.playerselection.Appbase.BaseView
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException

object NetworkManager {
    fun <T> requestData(
        call: retrofit2.Call<CommonResponseModel<T>>,
        baseView: BaseView,
        callback: MutableLiveData<RequestState<T>>

    )  {
        callback.postValue(RequestState(progress = true))
        call.enqueue(object : Callback<CommonResponseModel<T>> {
            override fun onFailure(call:  retrofit2.Call<CommonResponseModel<T>>, t: Throwable) {
                Timber.d("Retrofit onFailure ${t.localizedMessage}")
                if (call.isCanceled) {
                    Timber.d("request was cancelled")
                } else {
                    when (t) {
                        is HttpException -> {
                            val responseBody = t.response()?.errorBody()
                            baseView.onUnknownError(getErrorMessage(responseBody))
                        }
                        is SocketTimeoutException -> baseView.onTimeout()
                        is ConnectException -> baseView.onConnectionError()
                        is IOException -> baseView.onNetworkError()
                        else -> {
                            baseView.onUnknownError(t.message)
                        }
                    }
                    callback.value = RequestState(
                        progress = false,
                        error = ApiError(Config.CUSTOM_ERROR, t.message)
                    )
                }
            }

            override fun onResponse(
                call:  retrofit2.Call<CommonResponseModel<T>>,
                response: Response<CommonResponseModel<T>>
            ) {
                if (response.isSuccessful) {
                    /**
                     * based on status pass body
                     */
                    if (response.body()?.status == 1) {
                        callback.postValue(RequestState(apiResponse = response.body()))
                    } else {
                        /**
                         * incase of status is 0 or else ... not 1 will print body message
                         */
                        Log.e("network_message","Display network error message : "+response.message())
                        callback?.postValue(
                            RequestState(
                                error = ApiError(
                                    Config.CUSTOM_ERROR, response.body()?.message,response.code()
                                )
                            )
//                        callback.postValue(
//                            RequestState(
//                                error = ApiError(
//                                    Config.CUSTOM_ERROR, response.body()?.message
//                                )
//                            )
                        )
                    }
                } else {
                    /**
                     * pass unsuccessful message at here
                     */
                    callback.postValue(

                        RequestState(
                            error = ApiError(
                                Config.CUSTOM_ERROR, response.message(), code = response.code()
                            )
                        )
//                    callback.postValue(
//                        RequestState(
//                            error = ApiError(
//                                Config.CUSTOM_ERROR, response.message()
//                            )
//                        )
                    )


                }
            }
        })
    }

    fun getErrorMessage(responseBody: ResponseBody?): String? {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            jsonObject.getString("message")
        } catch (e: Exception) {
            e.message
        }
    }
}
