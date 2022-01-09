package com.setianjay.academy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.data.ModuleEntity
import com.setianjay.academy.data.source.remote.repository.AcademyRepository
import com.setianjay.academy.utils.DataDummy
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailCourseViewModelTest {
    private lateinit var viewModel: DetailCourseViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var courseObserver: Observer<CourseEntity>

    @Mock
    private lateinit var modulesObserver: Observer<List<ModuleEntity>>

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
        val courseResult = MutableLiveData<CourseEntity>()
        courseResult.value = dummyCourses

        `when`(academyRepository.getDetailCourse(courseId)).thenReturn(courseResult)
        val course = viewModel.getCourse().value
        verify(academyRepository).getDetailCourse(courseId)
        assertNotNull(course)
        assertEquals(dummyCourses.courseId, course?.courseId)
        assertEquals(dummyCourses.title, course?.title)
        assertEquals(dummyCourses.imagePath, course?.imagePath)
        assertEquals(dummyCourses.description, course?.description)
        assertEquals(dummyCourses.deadline, course?.deadline)

        viewModel.getCourse().observeForever(courseObserver)
        verify(courseObserver).onChanged(dummyCourses)
    }

    /**
     * check modules data list equals with data resources list
     * */
    @Test
    fun getModules(){
        val modulesResult = MutableLiveData<List<ModuleEntity>>()
        modulesResult.value = dummyModules

        `when`(academyRepository.getAllModules(courseId)).thenReturn(modulesResult)
        val modules = viewModel.getModules().value
        verify(academyRepository).getAllModules(courseId)
        assertNotNull(modules)
        assertArrayEquals(this.dummyModules.toTypedArray(), modules?.toTypedArray())

        viewModel.getModules().observeForever(modulesObserver)
        verify(modulesObserver).onChanged(dummyModules)
    }
}