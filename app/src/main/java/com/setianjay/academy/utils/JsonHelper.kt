package com.setianjay.academy.utils

import android.content.Context
import com.setianjay.academy.constant.FileConst
import com.setianjay.academy.data.source.remote.response.ContentResponse
import com.setianjay.academy.data.source.remote.response.CourseResponse
import com.setianjay.academy.data.source.remote.response.ModuleResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    /**
     * parsing file json to string
     *
     * @param fileName      name of json file
     * @return              string of json
     * */
    private fun parsingJsonToString(fileName: String): String? {
        try {
            return if (fileName.isNotEmpty()) {
                val file = context.assets.open(fileName)
                val buffer = ByteArray(file.available())
                file.read(buffer)
                file.close()
                String(buffer)
            } else {
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * load data courses from local data json
     *
     * @return          list of courses
     * */
    fun loadCourses(): List<CourseResponse> {
        val listCourses: ArrayList<CourseResponse> = ArrayList()
        try {
            val responseObject = JSONObject(parsingJsonToString(FileConst.COURSE_FILE).toString())
            val listResponseCourses = responseObject.getJSONArray(COURSES)
            for (i in 0 until listResponseCourses.length()){
                val course = listResponseCourses.getJSONObject(i)

                val id = course.getString(ID)
                val title = course.getString(TITLE)
                val description = course.getString(DESCRIPTION)
                val date = course.getString(DATE)
                val imagePath = course.getString(IMAGE_PATH)

                val courseResponse = CourseResponse(id, title, description, date, imagePath)
                listCourses.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return listCourses
    }

    /**
     * load data modules from local data json
     *
     * @param courseId      id of course
     * @return              list of modules based on course id
     * */
    fun loadModules(courseId: String): List<ModuleResponse>{
        val listModules: ArrayList<ModuleResponse> = ArrayList()
        val fileName = String.format(FileConst.MODULES_FILE, courseId)
        try {
            parsingJsonToString(fileName)?.let{ json ->
                val responseObject = JSONObject(json)
                val listResponseModules = responseObject.getJSONArray(MODULES)

                for (i in 0 until listResponseModules.length()){
                    val module = listResponseModules.getJSONObject(i)

                    val moduleId = module.getString(MODULE_ID)
                    val title = module.getString(TITLE)
                    val position = module.getString(POSITION).toInt()

                    val moduleResponse = ModuleResponse(moduleId, courseId, title, position)
                    listModules.add(moduleResponse)
                }
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return listModules
    }

    /**
     * load data content from local data json
     *
     * @param moduleId      id of module
     * @return              content
     * */
    fun loadContent(moduleId: String): ContentResponse{
        lateinit var contentResponse: ContentResponse
        val fileName = String.format(FileConst.CONTENT_FILE, moduleId)
        try {
            parsingJsonToString(fileName)?.let { json ->
                val responseObject = JSONObject(json)

                val content = responseObject.getString(CONTENT)

                contentResponse = ContentResponse(moduleId, content)
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return contentResponse
    }

    companion object{

        private const val CONTENT = "content"
        private const val COURSES = "courses"
        private const val MODULES = "modules"

        private const val DATE = "date"
        private const val DESCRIPTION = "description"
        private const val ID = "id"
        private const val IMAGE_PATH = "imagePath"
        private const val MODULE_ID = "moduleId"
        private const val POSITION = "position"
        private const val TITLE = "title"
    }
}