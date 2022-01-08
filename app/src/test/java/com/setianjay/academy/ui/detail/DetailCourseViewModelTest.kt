package com.setianjay.academy.ui.detail

import com.setianjay.academy.data.source.remote.repository.AcademyRepository
import com.setianjay.academy.utils.DataDummy
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailCourseViewModelTest {
    private lateinit var viewModel: DetailCourseViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository

    private val dummyCourses = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourses.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setSelectedCourse(courseId)
    }

    /**
     * check course data equals with data resources
     * */
    @Test
    fun getCourse() {
        `when`(academyRepository.getDetailCourse(courseId)).thenReturn(dummyCourses)
        val course = viewModel.getCourse()
        verify(academyRepository).getDetailCourse(courseId)
        assertNotNull(course)
        assertEquals(dummyCourses.courseId, course.courseId)
        assertEquals(dummyCourses.title, course.title)
        assertEquals(dummyCourses.imagePath, course.imagePath)
        assertEquals(dummyCourses.description, course.description)
        assertEquals(dummyCourses.deadline, course.deadline)
    }

    /**
     * check modules data list equals with data resources list
     * */
    @Test
    fun getModules(){
        `when`(academyRepository.getAllModules(courseId)).thenReturn(dummyModules)
        val modules = viewModel.getModules()
        verify(academyRepository).getAllModules(courseId)
        assertNotNull(modules)
        assertArrayEquals(this.dummyModules.toTypedArray(), modules.toTypedArray())
    }
}