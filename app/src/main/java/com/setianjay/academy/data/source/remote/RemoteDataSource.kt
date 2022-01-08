package com.setianjay.academy.data.source.remote

import android.os.Handler
import android.os.Looper
import com.setianjay.academy.data.source.remote.response.ContentResponse
import com.setianjay.academy.data.source.remote.response.CourseResponse
import com.setianjay.academy.data.source.remote.response.ModuleResponse
import com.setianjay.academy.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(jsonHelper: JsonHelper): RemoteDataSource {
            return instance ?: synchronized(this) {
                instance ?: RemoteDataSource(jsonHelper).apply { instance = this }
            }
        }
    }

    interface LoadCoursesCallback {
        fun onAllCoursesReceived(courses: List<CourseResponse>)
    }

    interface LoadModulesCallback {
        fun onAllModulesReceived(modules: List<ModuleResponse>)
    }

    interface LoadContentModule {
        fun onContentModuleReceived(content: ContentResponse)
    }

    /**
     * get all courses data
     *
     * @param callback          callback for hold all courses data
     * @output                  response content from callback parameter and pass to the caller
     * */
    fun getAllCourse(callback: LoadCoursesCallback) {
        handler.postDelayed(
            { callback.onAllCoursesReceived(jsonHelper.loadCourses()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    /**
     * get all modules data
     *
     * @param courseId          id of course
     * @param callback          callback for hold all modules data
     * @output                  response content from callback parameter and pass to the caller
     * */
    fun getAllModules(courseId: String, callback: LoadModulesCallback) {
        handler.postDelayed(
            { callback.onAllModulesReceived(jsonHelper.loadModules(courseId)) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    /**
     * get content of module
     *
     * @param moduleId          id of module
     * @param callback          callback for hold the content of modules data
     * @output                  response content from callback parameter and pass to the caller
     * */
    fun getContent(moduleId: String, callback: LoadContentModule) {
        handler.postDelayed(
            { callback.onContentModuleReceived(jsonHelper.loadContent(moduleId)) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }
}