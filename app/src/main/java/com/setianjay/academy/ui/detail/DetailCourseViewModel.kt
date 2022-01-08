package com.setianjay.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.data.ModuleEntity
import com.setianjay.academy.data.source.remote.repository.AcademyRepository

class DetailCourseViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String){
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity {
        return academyRepository.getDetailCourse(courseId)
    }


    fun getModules(): List<ModuleEntity> = academyRepository.getAllModules(courseId)
}