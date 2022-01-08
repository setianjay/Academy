package com.setianjay.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.data.source.remote.repository.AcademyRepository
import com.setianjay.academy.utils.DataDummy

class AcademyViewModel(private val academyRepository: AcademyRepository): ViewModel() {

    fun getCourses(): List<CourseEntity> = academyRepository.getAllCourses()
}