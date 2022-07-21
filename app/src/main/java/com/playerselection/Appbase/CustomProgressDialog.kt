package com.playerselection.Appbase

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.playerselection.R

class CustomProgressDialog(context: Context) : Dialog(context, R.style.full_screen_dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.progress_dialog)

    }
}