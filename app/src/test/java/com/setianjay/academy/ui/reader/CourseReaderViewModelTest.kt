package com.setianjay.academy.ui.reader

import com.setianjay.academy.utils.DataDummy
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class CourseReaderViewModelTest {
    private lateinit var viewModel: CourseReaderViewModel
    private val dummyCourses = DataDummy.generateDummyCourses()

    private val courseId = dummyCourses[0].courseId

    private val dummyModules = DataDummy.generateDummyModules(courseId)
    private val moduleId = dummyModules[0].moduleId

    private val dummyContent =
        "<h3 class=\\\"fr-text-bordered\\\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"

    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel()
        viewModel.selectedCourse(courseId)
        viewModel.selectedModule(moduleId)
    }

    /**
     * check modules data list equals with data resources list
     * */
    @Test
    fun getModules() {
        val modules = viewModel.getModules()
        assertNotNull(modules)
        assertArrayEquals(dummyModules.toTypedArray(), modules.toTypedArray())
    }

    /**
     * check content data equals with content data resources
     * */
    @Test
    fun getSelectedModule() {
        val moduleEntity = viewModel.getSelectedModule()
        assertNotNull(moduleEntity)

        val contentEntity = moduleEntity.contentEntity
        assertNotNull(contentEntity)

        val content = contentEntity?.content
        assertNotNull(content)
        assertEquals(dummyContent, content)
    }
}