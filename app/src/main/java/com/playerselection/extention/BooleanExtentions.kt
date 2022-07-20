
package com.playerselection.extention

import android.view.View

fun Boolean.isVisible() = if (this) View.VISIBLE else View.GONE