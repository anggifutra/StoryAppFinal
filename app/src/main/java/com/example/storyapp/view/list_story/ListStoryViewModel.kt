package com.example.storyapp.view.list_story

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.storyapp.data.*
import com.example.storyapp.view.list_story.ListStoryActivity.Companion.TAG
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListStoryViewModel(private val storyRepository: StoryRepository) : ViewModel() {
//    class ListStoryViewModel(private val pref: UserPreference) : ViewModel() {

    private val _listStory = MutableLiveData<List<ListStory>>()
    val listStory: LiveData<List<ListStory>> = _listStory

    fun getStory(token: String) {
        viewModelScope.launch {
            _listStory.postValue(storyRepository.getStory(token))
        }
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

//    fun getUser(): LiveData<UserLogin> {
//        return pref.getUser().asLiveData()
//    }

//    fun getStory(token: String) {
//        _isLoading.value = true
//        val client = ApiConfig.getApiService().getStory(1,20,"Bearer $token")
//        client.enqueue(object : Callback<ResponseStory> {
//            override fun onResponse(
//                call: Call<ResponseStory>,
//                response: Response<ResponseStory>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        _listStory.value = response.body()?.listStory
//                    }
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//            override fun onFailure(call: Call<ResponseStory>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }

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