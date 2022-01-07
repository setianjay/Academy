package com.setianjay.academy.data.source.remote

import com.setianjay.academy.data.source.remote.response.ContentResponse
import com.setianjay.academy.data.source.remote.response.CourseResponse
import com.setianjay.academy.data.source.remote.response.ModuleResponse
import com.setianjay.academy.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(jsonHelper: JsonHelper): RemoteDataSource{
            return instance ?: synchronized(this){
                instance ?: RemoteDataSource(jsonHelper).apply { instance = this }
            }
        }
    }

    /**
     * get all courses data
     *
     * @return list of courses
     * */
    fun getAllCourse(): List<CourseResponse>{
        return jsonHelper.loadCourses()
    }

    /**
     * get all modules data
     *
     * @param courseId          id of course
     * @return                  list of modules
     * */
    fun getAllModules(courseId: String): List<ModuleResponse>{
        return jsonHelper.loadModules(courseId)
    }

    /**
     * get content of module
     *
     * @param moduleId          id of module
     * @return                  response content
     * */
    fun getContent(moduleId: String): ContentResponse{
        return jsonHelper.loadContent(moduleId)
    }
}