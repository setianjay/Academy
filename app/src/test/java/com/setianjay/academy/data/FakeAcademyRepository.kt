package com.setianjay.academy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.setianjay.academy.data.source.remote.RemoteDataSource
import com.setianjay.academy.data.source.remote.repository.IDataSource
import com.setianjay.academy.data.source.remote.response.ContentResponse
import com.setianjay.academy.data.source.remote.response.CourseResponse
import com.setianjay.academy.data.source.remote.response.ModuleResponse

class FakeAcademyRepository (private val remoteDataSource: RemoteDataSource) :
    IDataSource {

    /**
     * obtain all courses
     *
     * @return array of list course
     * */
    override fun getAllCourses(): LiveData<List<CourseEntity>> {
        val coursesResult = MutableLiveData<List<CourseEntity>>()

        remoteDataSource.getAllCourse(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(courses: List<CourseResponse>) {
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
                    coursesResult.postValue(listCourse)
                }
            }
        })
        return coursesResult
    }

    /**
     * obtain all bookmarked courses
     *
     * @return array of list bookmarked courses
     * */
    override fun getBookmarkedCourses(): LiveData<List<CourseEntity>> {
        val bookmarkedResult = MutableLiveData<List<CourseEntity>>()

        remoteDataSource.getAllCourse(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(courses: List<CourseResponse>) {
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
                    bookmarkedResult.postValue(listCourse)
                }
            }
        })
        return bookmarkedResult
    }

    /**
     * obtain detail course
     *
     * @param courseId      id of course
     * @return              entity of course
     * */
    override fun getDetailCourse(courseId: String): LiveData<CourseEntity> {
        val detailResult = MutableLiveData<CourseEntity>()

        remoteDataSource.getAllCourse(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(courses: List<CourseResponse>) {
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
                        detailResult.postValue(courseEntity)
                    }
                }
            }
        })
        return detailResult
    }

    /**
     * obtain all modules
     *
     * @param courseId          id of course
     * @return                  list of module
     * */
    override fun getAllModules(courseId: String): LiveData<List<ModuleEntity>> {
        val modulesResult = MutableLiveData<List<ModuleEntity>>()

        remoteDataSource.getAllModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(modules: List<ModuleResponse>) {
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
                    modulesResult.postValue(listModules)
                }
            }
        })
        return modulesResult
    }

    /**
     * obtain content of module
     *
     * @param courseId          id of course
     * @param moduleId          id of module
     *
     * @return                  entity of module
     * */
    override fun getContentModule(courseId: String, moduleId: String): LiveData<ModuleEntity> {
        val contentResult = MutableLiveData<ModuleEntity>()

        remoteDataSource.getAllModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(modules: List<ModuleResponse>) {
                for (i in modules) {
                    if (moduleId == i.moduleId) {
                        val module = ModuleEntity(
                            i.moduleId,
                            i.courseId,
                            i.title,
                            i.position,
                            false
                        )
                        remoteDataSource.getContent(moduleId, object :
                            RemoteDataSource.LoadContentModule {
                            override fun onContentModuleReceived(content: ContentResponse) {
                                module.contentEntity = ContentEntity(content.content)
                                contentResult.postValue(module)
                            }
                        })
                        break
                    }
                }
            }
        })
        return contentResult
    }
}