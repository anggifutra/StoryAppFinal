package com.example.storyapp.view.list_story

import android.content.Context
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.storyapp.data.*

class ListStoryViewModel(private val storyRepository: StoryRepository) : ViewModel() {

    fun getStory(token: String) : LiveData<PagingData<ListStory>>{
        return storyRepository.getStory(token).cachedIn(viewModelScope)
    }
}

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListStoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListStoryViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}