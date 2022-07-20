package com.playerselection.extention

fun String.removeUnnecessaryDecimalPoint(): String {
    return if (this.contains(".00")) {
        this.split(".")[0]
    } else this
}

fun String.isValid(): Boolean {
    return !this.isNullOrEmpty()
}