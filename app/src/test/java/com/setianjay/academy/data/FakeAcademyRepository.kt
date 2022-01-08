package com.setianjay.academy.data

import com.setianjay.academy.data.source.remote.RemoteDataSource
import com.setianjay.academy.data.source.remote.repository.IDataSource

class FakeAcademyRepository (private val remoteDataSource: RemoteDataSource) :
    IDataSource {

    /**
     * obtain all courses
     *
     * @return array of list course
     * */
    override fun getAllCourses(): List<CourseEntity> {
        val courses = remoteDataSource.getAllCourse()
        val listCourse: ArrayList<CourseEntity> = ArrayList()

        for (i in courses) {
            val course = CourseEntity(
                i.id,
                i.title,
                i.description,
                i.date,
                false,
                i.imagePath
            )
            listCourse.add(course)
        }
        return listCourse
    }

    /**
     * obtain all bookmarked courses
     *
     * @return array of list bookmarked courses
     * */
    override fun getBookmarkedCourses(): List<CourseEntity> {
        val courses = remoteDataSource.getAllCourse()
        val listCourse: ArrayList<CourseEntity> = ArrayList()

        for (i in courses) {
            val course = CourseEntity(
                i.id,
                i.title,
                i.description,
                i.date,
                false,
                i.imagePath
            )
            listCourse.add(course)
        }
        return listCourse
    }

    /**
     * obtain detail course
     *
     * @param courseId      id of course
     * @return              entity of course
     * */
    override fun getDetailCourse(courseId: String): CourseEntity {
        val courses = remoteDataSource.getAllCourse()
        lateinit var courseEntity: CourseEntity

        for (i in courses) {
            if (i.id == courseId) {
                val course = CourseEntity(
                    i.id,
                    i.title,
                    i.description,
                    i.date,
                    false,
                    i.imagePath
                )
                courseEntity = course
            }
        }
        return courseEntity
    }

    /**
     * obtain all modules
     *
     * @param courseId          id of course
     * @return                  list of module
     * */
    override fun getAllModules(courseId: String): List<ModuleEntity> {
        val modules = remoteDataSource.getAllModules(courseId)
        val listModules: ArrayList<ModuleEntity> = ArrayList()

        for (i in modules) {
            val module = ModuleEntity(
                i.moduleId,
                i.courseId,
                i.title,
                i.position,
                false
            )

            listModules.add(module)
        }

        return listModules
    }

    /**
     * obtain content of module
     *
     * @param courseId          id of course
     * @param moduleId          id of module
     *
     * @return                  entity of module
     * */
    override fun getContentModule(courseId: String, moduleId: String): ModuleEntity {
        val modules = remoteDataSource.getAllModules(courseId)
        lateinit var module: ModuleEntity

        for (i in modules) {
            if (moduleId == i.moduleId) {
                module = ModuleEntity(
                    i.moduleId,
                    i.courseId,
                    i.title,
                    i.position,
                    false
                ).apply {
                    contentEntity = ContentEntity(remoteDataSource.getContent(moduleId).content)
                }
                break
            }
        }
        return module
    }
}