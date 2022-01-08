package com.setianjay.academy.data

import com.setianjay.academy.data.source.remote.RemoteDataSource
import com.setianjay.academy.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class AcademyRepositoryTest {

    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remoteDataSource)

    //dummy data
    private val coursesResponse = DataDummy.generateRemoteDummyCourses()
    private val courseId = coursesResponse[0].id

    private val modulesResponse = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = modulesResponse[0].moduleId

    private val contentResponse = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses(){
        `when`(remoteDataSource.getAllCourse()).thenReturn(coursesResponse)
        val courses = academyRepository.getAllCourses()
        verify(remoteDataSource).getAllCourse()
        assertNotNull(courses)
        assertEquals(coursesResponse.size.toLong(), courses.size.toLong())
    }

    @Test
    fun getAllModules(){
        `when`(remoteDataSource.getAllModules(courseId)).thenReturn(modulesResponse)
        val modules = academyRepository.getAllModules(courseId)
        verify(remoteDataSource).getAllModules(courseId)
        assertNotNull(modules)
        assertEquals(modulesResponse.size.toLong(), modules.size.toLong())
    }

    @Test
    fun getBookmarkedCourses(){
        `when`(remoteDataSource.getAllCourse()).thenReturn(coursesResponse)
        val bookmarkedCourses = academyRepository.getBookmarkedCourses()
        verify(remoteDataSource).getAllCourse()
        assertNotNull(bookmarkedCourses)
        assertEquals(coursesResponse.size.toLong(), bookmarkedCourses.size.toLong())
    }

    @Test
    fun getDetailCourse(){
        `when`(remoteDataSource.getAllCourse()).thenReturn(coursesResponse)
        val detailCourse = academyRepository.getDetailCourse(courseId)
        verify(remoteDataSource).getAllCourse()
        assertNotNull(detailCourse)
        assertEquals(coursesResponse[0].title, detailCourse.title)
    }

    @Test
    fun getContentModule(){
        `when`(remoteDataSource.getAllModules(courseId)).thenReturn(modulesResponse)
        `when`(remoteDataSource.getContent(moduleId)).thenReturn(contentResponse)
        val content = academyRepository.getContentModule(courseId, moduleId)
        verify(remoteDataSource).getContent(moduleId)
        assertNotNull(content)
        assertEquals(contentResponse.content, content.contentEntity?.content)
    }
}