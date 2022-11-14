package com.example.storyapp.data

import retrofit2.Call

class StoryRepository(private val storyDatabase: StoryDatabase,private val apiService: ApiService) {
    suspend fun getStory(token: String): Call<ResponseStory> {
        return apiService.getStory("Bearer $token")
    }
}