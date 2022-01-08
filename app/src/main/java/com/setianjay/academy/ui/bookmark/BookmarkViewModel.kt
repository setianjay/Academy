package com.setianjay.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.data.source.remote.repository.AcademyRepository
import com.setianjay.academy.utils.DataDummy

class BookmarkViewModel(private val academyRepository: AcademyRepository): ViewModel() {

    fun getBookmarks(): List<CourseEntity> = academyRepository.getBookmarkedCourses()
}