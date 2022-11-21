package com.example.storyapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryRepository(private val storyDatabase: StoryDatabase,private val apiService: ApiService) {
    fun getStory(token: String): LiveData<PagingData<ListStory>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = StoryRemoteMediator(token,storyDatabase, apiService),
            pagingSourceFactory = {
                storyDatabase.storyDao().getAllStory()
            }
        ).liveData
    }

//    fun getMap(token: String): List<ListStory>{
//        val client = ApiConfig.getApiService().getMaps("Bearer $token")
//        val clientdata = client.listStory
//            override fun onResponse(
//                call: Call<ResponseStory>,
//                response: Response<ResponseStory>
//            ) {
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        response.body()?.listStory
//                    }
//                }
//            }
//            override fun onFailure(call: Call<ResponseStory>, t: Throwable) {}
//        })
//        return item
//    }
    fun getMap(token: String): LiveData<Result<List<ListStory>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getMaps("Bearer $token")
            val responseData = response.listStory
            val liststory = responseData.map { story ->
                ListStory(
                    story.id,
                    story.name,
                    story.description,
                    story.photoUrl,
                    story.lat,
                    story.lon
                )
            }
            emit(Result.Success(liststory))
        } catch (e: Exception) {
            Log.d("StoryRepository", "getMap: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }
}
