package com.setianjay.academy.utils

import android.content.Context

object TextUtils {

    fun getStringFromRes(context: Context, id: Int): String {
        return context.getString(id)
    }
}