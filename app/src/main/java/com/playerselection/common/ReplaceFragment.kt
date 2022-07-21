package com.playerselection.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

object ReplaceFragment {
    val transaction: FragmentTransaction? = null

/*
    fun homeFragmentReplace(currentActivity : HomeActivity,fragment: Fragment,backStackName : String?){
        val transaction  = currentActivity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flUserContainer, fragment)
        if(backStackName.equals(null)){
            transaction.addToBackStack(null)
        }
        else{
            transaction.addToBackStack(backStackName)
        }

        transaction.commit()
    }*/

    fun replaceFragment(
        activity: FragmentActivity?,
        fragment: Fragment?,
        isAddToBackStack: Boolean,
        backStackName: String,
        tagName: String? = null
    ) {
//        try {
//            fragment?.let { mFragment ->
//                if (isAddToBackStack) {
//                    activity?.supportFragmentManager?.beginTransaction()
////                        ?.setCustomAnimations(R.anim.rightto, R.anim.left)
//                        ?.replace(R.id.flUserContainer, mFragment, tagName)
//                        ?.addToBackStack(backStackName)?.commitAllowingStateLoss()
//
//                } else {
//                    activity?.supportFragmentManager?.beginTransaction()
//                        ?.replace(R.id.flUserContainer, mFragment, tagName)
//                        ?.commitAllowingStateLoss()
//
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }
}
