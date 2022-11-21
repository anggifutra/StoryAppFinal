package com.example.storyapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState


class StoryPagingSource(private val apiService: ApiService,private val token: String) : PagingSource<Int, ListStory>() {

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
            val response = apiService.getStory(position, params.loadSize,"Bearer $token")
            val responseData = response.listStory
            LoadResult.Page(
                data = responseData,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (responseData.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}
