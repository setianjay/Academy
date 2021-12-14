package com.setianjay.academy.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.setianjay.academy.databinding.ActivityDetailCourseBinding

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object{
        const val EXTRA_COURSE = "extra_course"
    }
}