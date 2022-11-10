package com.example.storyapp.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.storyapp.view.list_story.ListStoryActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryPagingSource(private val apiService: ApiService)  : PagingSource<Int, ListStory>(){

    private lateinit var liststory: List<ListStory>

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ListStory>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListStory> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getStory(position, params.loadSize, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLVl2SEtuSms5T1lyVk9OMDIiLCJpYXQiOjE2Njc4OTMzMjJ9.63ENJeMTHXxVgrBWHI91H6EVZctjOGkWt3DVrpeAnWE")
            responseData.enqueue(object : Callback<ResponseStory> {
                override fun onResponse(
                    call: Call<ResponseStory>,
                    response: Response<ResponseStory>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            liststory = response.body()?.listStory!!
                        }
                    } else {
                        Log.e(ListStoryActivity.TAG, "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<ResponseStory>, t: Throwable) {
                    Log.e(ListStoryActivity.TAG, "onFailure: ${t.message}")
                }
            })
            LoadResult.Page(
                data = liststory,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (liststory.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}