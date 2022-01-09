package com.setianjay.academy.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.setianjay.academy.R
import com.setianjay.academy.utils.DataDummy

import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyCourses = DataDummy.generateDummyCourses()
    private val dummyModules = DataDummy.generateDummyModules(dummyCourses[0].courseId)

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    /**
     * ui test for load course in home activity
     * */
    @Test
    fun loadCourses() {
        delayTwoSecond()
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourses.size))
    }

    /**
     * ui test for load course detail in detail activity
     * */
    @Test
    fun loadCourseDetail(){
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        delayTwoSecond()
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delayTwoSecond()
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyCourses[0].title)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText("Deadline ${dummyCourses[0].deadline}")))
    }

    /**
     * ui test for load module in reader activity
     * */
    @Test
    fun loadModule(){
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        delayTwoSecond()
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_start)).check(matches(isDisplayed()))
        delayTwoSecond()
        onView(withId(R.id.btn_start)).perform(click())
        delayTwoSecond()
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyModules.size))
    }

    /**
     * ui test for load module content in reader activity
     * */
    @Test
    fun loadModuleContent(){
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        delayTwoSecond()
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delayTwoSecond()
        onView(withId(R.id.btn_start)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_start)).perform(click())
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        delayTwoSecond()
        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delayTwoSecond()
        onView(withId(R.id.wb_view)).check(matches(isDisplayed()))
        delayTwoSecond()
    }

    /**
     * ui test for load bookmarks in home activity
     * */
    @Test
    fun loadBookmarks(){
        onView(withText("Bookmark")).perform(click())
        delayTwoSecond()
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_bookmark)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourses.size))
    }

    private fun delayTwoSecond(){
        try{
            Thread.sleep(2000L)
        }catch (e: InterruptedException){
            e.printStackTrace()
        }
    }
}