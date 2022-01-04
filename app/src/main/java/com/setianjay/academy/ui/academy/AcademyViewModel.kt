package com.setianjay.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.utils.DataDummy

class AcademyViewModel: ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}