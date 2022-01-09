package com.setianjay.academy.data

import com.setianjay.academy.LiveDataUtil
import com.setianjay.academy.data.source.remote.RemoteDataSource
import com.setianjay.academy.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq

class AcademyRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remoteDataSource)

    //dummy data
    private val coursesResponse = DataDummy.generateRemoteDummyCourses()
    private val courseId = coursesResponse[0].id

    private val modulesResponse = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = modulesResponse[0].moduleId

    private val contentResponse = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCoursesCallback)
                .onAllCoursesReceived(coursesResponse)
            null
        }.`when`(remoteDataSource).getAllCourse(any())

        val courses = LiveDataUtil.getValue(academyRepository.getAllCourses())
        verify(remoteDataSource).getAllCourse(any())
        assertNotNull(courses)
        assertEquals(coursesResponse.size.toLong(), courses.size.toLong())
    }

    @Test
    fun getAllModules(){
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadModulesCallback).
            onAllModulesReceived(modulesResponse)
            null
        }.`when`(remoteDataSource).getAllModules(eq(courseId), any())

        val modules = LiveDataUtil.getValue(academyRepository.getAllModules(courseId))
        verify(remoteDataSource).getAllModules(eq(courseId), any())
        assertNotNull(modules)
        assertEquals(modulesResponse.size.toLong(), modules.size.toLong())
    }

    @Test
    fun getBookmarkedCourses(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCoursesCallback).
            onAllCoursesReceived(coursesResponse)
            null
        }.`when`(remoteDataSource).getAllCourse(any())

        val bookmarkedCourses = LiveDataUtil.getValue(academyRepository.getBookmarkedCourses())
        verify(remoteDataSource).getAllCourse(any())
        assertNotNull(bookmarkedCourses)
        assertEquals(coursesResponse.size.toLong(), bookmarkedCourses.size.toLong())
    }

    @Test
    fun getDetailCourse(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCoursesCallback).
            onAllCoursesReceived(coursesResponse)
            null
        }.`when`(remoteDataSource).getAllCourse(any())

        val detailCourse = LiveDataUtil.getValue(academyRepository.getDetailCourse(courseId))
        verify(remoteDataSource).getAllCourse(any())
        assertNotNull(detailCourse)
        assertEquals(coursesResponse[0].title, detailCourse.title)
    }

    @Test
    fun getContentModule(){
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadModulesCallback).
            onAllModulesReceived(modulesResponse)
            null
        }.`when`(remoteDataSource).getAllModules(eq(courseId), any())

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadContentModule).
            onContentModuleReceived(contentResponse)
            null
        }.`when`(remoteDataSource).getContent(eq(moduleId), any())

        val content = LiveDataUtil.getValue(academyRepository.getContentModule(courseId, moduleId))
        verify(remoteDataSource).getAllModules(eq(courseId), any())
        verify(remoteDataSource).getContent(eq(moduleId), any())
        assertNotNull(content)
        assertEquals(contentResponse.content, content.contentEntity?.content)
    }
}