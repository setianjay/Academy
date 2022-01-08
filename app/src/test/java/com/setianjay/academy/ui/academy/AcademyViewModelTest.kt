package com.setianjay.academy.ui.academy

import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.data.source.remote.repository.AcademyRepository
import com.setianjay.academy.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository

    private val dummyCourses = DataDummy.generateDummyCourses()

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    /**
     * check the courses data size is equals with data resources size
     **/
    @Test
    fun getCourses() {
        `when`(academyRepository.getAllCourses()).thenReturn(dummyCourses as ArrayList<CourseEntity>)
        //call function in view model to obtain courses data
        val courses = viewModel.getCourses()
        verify(academyRepository).getAllCourses()
        //assert to not null
        assertNotNull(courses)
        //assert that the size is equals with the data resources size
        assertEquals(dummyCourses.size, courses.size)
    }
}