package com.setianjay.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.utils.DataDummy

class BookmarkViewModel: ViewModel() {

    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourses()
}