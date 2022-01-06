package com.setianjay.academy.ui.academy

import com.setianjay.academy.utils.DataDummy
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel
    private val dummyCourses = DataDummy.generateDummyCourses()

    @Before
    fun setUp() {
        viewModel = AcademyViewModel()
    }

    /**
     * check the courses data size is equals with data resources size
     **/
    @Test
    fun getCourses() {
        //call function in view model to obtain courses data
        val courses = viewModel.getCourses()
        //assert to not null
        assertNotNull(courses)
        //assert that the size is equals with the data resources size
        assertEquals(dummyCourses.size, courses.size)
    }
}