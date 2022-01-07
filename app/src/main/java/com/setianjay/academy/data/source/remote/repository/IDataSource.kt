package com.setianjay.academy.data.source.remote.repository

import com.setianjay.academy.data.ContentEntity
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.data.ModuleEntity

interface IDataSource {

    fun getAllCourses(): List<CourseEntity>

    fun getBookmarkedCourses(): List<CourseEntity>

    fun getDetailCourse(courseId: String): CourseEntity

    fun getAllModules(courseId: String): List<ModuleEntity>

    fun getContentModule(courseId: String, moduleId: String): ModuleEntity
}