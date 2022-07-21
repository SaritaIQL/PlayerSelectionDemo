package com.playerselection.Appbase

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import com.playerselection.R

class ReusedMethod {
    companion object {
        fun displayMessage(context: Activity, message: String) {
            CustomToast.showToast(
                context, message,
                true,
                ContextCompat.getColor(context, R.color.white),
                ContextCompat.getColor(context, R.color.black),
                true
            )
        }

        fun isNetworkConnected(context: Context): Boolean {
            if (context != null) {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                return if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    true
                } else {
                    CustomToast.showToast(
                        context, context.resources.getString(R.string.internet_message_alert), true,
                        ContextCompat.getColor(context, R.color.white),
                        ContextCompat.getColor(context, R.color.black), true
                    )

                    false
                }
            }
            return false
        }

    }

}