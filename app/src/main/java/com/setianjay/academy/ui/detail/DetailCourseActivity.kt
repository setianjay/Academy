package com.setianjay.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.setianjay.academy.R
import com.setianjay.academy.adapter.DetailCourseAdapter
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.databinding.ActivityDetailCourseBinding
import com.setianjay.academy.databinding.ContentDetailCourseBinding
import com.setianjay.academy.ui.reader.CourseReaderActivity

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCourseBinding
    private lateinit var detailContentBinding: ContentDetailCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCourseBinding.inflate(layoutInflater)
        detailContentBinding = binding.detailContent
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupRecycleView()

    }

    private fun setupRecycleView() {
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailCourseViewModel::class.java]
        val detailCourseAdapter = DetailCourseAdapter()

        val extras = intent.extras

        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                viewModel.setSelectedCourse(courseId)

                val course = viewModel.getCourse()
                populateCourse(course)

                val modules = viewModel.getModules()
                detailCourseAdapter.setModules(modules)

                detailContentBinding.rvModule.apply {
                    val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                    addItemDecoration(dividerItemDecoration)
                    isNestedScrollingEnabled = false
                    layoutManager = LinearLayoutManager(context)
                    adapter = detailCourseAdapter
                    setHasFixedSize(true)
                }
            }
        }

    }

    private fun populateCourse(courseEntity: CourseEntity) {
        detailContentBinding.textTitle.text = courseEntity.title
        detailContentBinding.textDescription.text = courseEntity.description
        detailContentBinding.textDate.text =
            resources.getString(R.string.deadline_date, courseEntity.deadline)

        Glide.with(this)
            .load(courseEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.imagePoster)

        detailContentBinding.btnStart.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
}