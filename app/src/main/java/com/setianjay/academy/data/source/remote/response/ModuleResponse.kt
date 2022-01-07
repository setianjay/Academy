package com.setianjay.academy.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModuleResponse(
    var moduleId: String,
    val courseId: String,
    var title: String,
    val position: Int
): Parcelable
