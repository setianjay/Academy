package com.setianjay.academy.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.setianjay.academy.data.ModuleEntity
import com.setianjay.academy.data.source.remote.repository.AcademyRepository

class CourseReaderViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun selectedCourse(courseId: String){
        this.courseId = courseId
    }

    fun selectedModule(moduleId: String){
        this.moduleId = moduleId
    }

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModules(courseId)

    fun getSelectedModule(): LiveData<ModuleEntity> = academyRepository.getContentModule(courseId, moduleId)

}