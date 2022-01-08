package com.setianjay.academy.data.source.remote.repository

import android.content.Context
import com.setianjay.academy.data.source.remote.RemoteDataSource
import com.setianjay.academy.utils.JsonHelper

object Injection {

    fun provideRepository(context: Context): AcademyRepository{

        val jsonHelper = JsonHelper(context)
        val remoteDataSource = RemoteDataSource.getInstance(jsonHelper)

        return AcademyRepository.getInstance(remoteDataSource)
    }
}