package com.setianjay.academy.ui.reader

import com.setianjay.academy.data.ContentEntity
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
class CourseReaderViewModelTest {
    private lateinit var viewModel: CourseReaderViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository

    private val dummyCourses = DataDummy.generateDummyCourses()
    private val courseId = dummyCourses[0].courseId

    private val dummyModules = DataDummy.generateDummyModules(courseId)
    private val moduleId = dummyModules[0].moduleId

    private val dummyContent = dummyModules[0]

    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel(academyRepository)
        viewModel.selectedCourse(courseId)
        viewModel.selectedModule(moduleId)
        dummyContent.contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + dummyContent.title + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
    }

    /**
     * check modules data list equals with data resources list
     * */
    @Test
    fun getModules() {
        `when`(academyRepository.getAllModules(courseId)).thenReturn(dummyModules)
        val modules = viewModel.getModules()
        verify(academyRepository).getAllModules(courseId)
        assertNotNull(modules)
        assertArrayEquals(dummyModules.toTypedArray(), modules.toTypedArray())
    }

    /**
     * check content data equals with content data resources
     * */
    @Test
    fun getSelectedModule() {
        `when`(academyRepository.getContentModule(courseId, moduleId)).thenReturn(dummyContent)
        val moduleEntity = viewModel.getSelectedModule()
        verify(academyRepository).getContentModule(courseId, moduleId)
        assertNotNull(moduleEntity)

        val contentEntity = moduleEntity.contentEntity
        assertNotNull(contentEntity)

        val content = contentEntity?.content
        assertNotNull(content)
        assertEquals(dummyContent.contentEntity?.content, content)
    }
}