package com.example.storyapp.data

import android.content.Context

object Injection {
    fun provideRepository(context: Context): StoryRepository{
        val database = StoryDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return StoryRepository(database,apiService)
    }
}