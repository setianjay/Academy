package com.setianjay.academy.data.source.remote.repository

import androidx.lifecycle.LiveData
import com.setianjay.academy.data.ContentEntity
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.data.ModuleEntity

interface IDataSource {

    fun getAllCourses(): LiveData<List<CourseEntity>>

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>>

    fun getDetailCourse(courseId: String): LiveData<CourseEntity>

    fun getAllModules(courseId: String): LiveData<List<ModuleEntity>>

    fun getContentModule(courseId: String, moduleId: String): LiveData<ModuleEntity>
}