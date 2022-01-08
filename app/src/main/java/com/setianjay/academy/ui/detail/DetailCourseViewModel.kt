package com.setianjay.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.data.ModuleEntity
import com.setianjay.academy.data.source.remote.repository.AcademyRepository
import com.setianjay.academy.utils.DataDummy

class DetailCourseViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String){
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity{
        lateinit var course: CourseEntity
        val coursesEntities = academyRepository.getAllCourses()
        for (courseEntity in coursesEntities){
            if (courseEntity.courseId == courseId){
                course = courseEntity
                break
            }
        }

        return course
    }


    fun getModules(): List<ModuleEntity> = academyRepository.getAllModules(courseId)
}