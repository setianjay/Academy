package com.setianjay.academy.ui.bookmark

import com.setianjay.academy.utils.DataDummy
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class BookmarkViewModelTest {
    private lateinit var viewModel: BookmarkViewModel
    private val dummyCourses = DataDummy.generateDummyCourses()

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel()
    }

    /**
     * check bookmarks data size equals with data resources size
     * */
    @Test
    fun getBookmarks() {
        val bookmarks = viewModel.getBookmarks()
        assertNotNull(bookmarks)
        assertEquals(dummyCourses.size, bookmarks.size)
    }
}