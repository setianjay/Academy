package com.setianjay.academy.ui.detail

import com.setianjay.academy.utils.DataDummy
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class DetailCourseViewModelTest {
    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourses = DataDummy.generateDummyCourses()
    private val courseId = dummyCourses[0].courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel()
        viewModel.setSelectedCourse(courseId)
    }

    /**
     * check course data equals with data resources
     * */
    @Test
    fun getCourse() {
        val course = viewModel.getCourse()
        assertNotNull(course)
        assertEquals(dummyCourses[0].courseId, course.courseId)
        assertEquals(dummyCourses[0].title, course.title)
        assertEquals(dummyCourses[0].imagePath, course.imagePath)
        assertEquals(dummyCourses[0].description, course.description)
        assertEquals(dummyCourses[0].deadline, course.deadline)
    }

    /**
     * check modules data list equals with data resources list
     * */
    @Test
    fun getModules(){
        val modules = viewModel.getModules()
        assertNotNull(modules)
        assertArrayEquals(this.dummyModules.toTypedArray(), modules.toTypedArray())
    }
}