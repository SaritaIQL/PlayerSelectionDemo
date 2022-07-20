package com.playerselection

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.playerselection.Appbase.BaseActivity
import com.playerselection.Appbase.ReusedMethod
import com.playerselection.Appbase.ReusedMethod.Companion.displayMessage
import com.playerselection.common.Config
import com.playerselection.databinding.ActivityMainBinding
import com.playerselection.util.ApiConstant
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val mViewModel: mainActivityModule by viewModel()
    override fun getResource(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mBinding = getBinding()



        mViewModel.getPlayerList(
            true,
            this@MainActivity,
        )
    }

    override fun initObserver() {
        mViewModel.getPlayerList().observe(this) { response ->
            response.let { requestState ->
                showLoadingIndicator(requestState.progress)
                requestState.apiResponse?.let {
                    it.data?.let { data ->
                        if (it.status==1) {

                           Log.e("dataApi","response : ${it.data.toString()}")
                        } else {
                            Log.e("dataApi","response not ")

                        }

                    }
                }

                requestState.error?.let { errorObj ->
                    when (errorObj.errorState) {
                        Config.NETWORK_ERROR ->
                            displayMessage(
                                 this,
                                getString(R.string.text_error_network)
                            )

                        Config.CUSTOM_ERROR ->
                            errorObj.customMessage
                                ?.let {
                                    if (errorObj.code == ApiConstant.API_401) {
                                        displayMessage(this, it)
                                    } else {

                                        displayMessage(this, it)
                                    }
                                }
                    }
                }
            }

        }

    }

    override fun handleListener() {
    }



}