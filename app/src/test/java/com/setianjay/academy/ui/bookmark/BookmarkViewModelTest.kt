package com.setianjay.academy.ui.bookmark

import com.setianjay.academy.data.CourseEntity
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
class BookmarkViewModelTest {
    private lateinit var viewModel: BookmarkViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository

    private val dummyCourses = DataDummy.generateDummyCourses()

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    /**
     * check bookmarks data size equals with data resources size
     * */
    @Test
    fun getBookmarks() {
        `when`(academyRepository.getBookmarkedCourses()).thenReturn(dummyCourses as ArrayList<CourseEntity>)
        val bookmarks = viewModel.getBookmarks()
        verify(academyRepository).getBookmarkedCourses()
        assertNotNull(bookmarks)
        assertEquals(dummyCourses.size, bookmarks.size)
    }
}