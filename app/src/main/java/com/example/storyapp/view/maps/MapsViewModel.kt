package com.example.storyapp.view.maps

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.storyapp.data.*
import com.example.storyapp.view.list_story.ListStoryActivity
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsViewModel(private val storyRepository: StoryRepository): ViewModel() {
//    private val _mapsData = MutableLiveData<List<ListStory>>()
//    val mapsData: LiveData<List<ListStory>> = _mapsData

    fun getMap(token: String) = storyRepository.getMap(token)

//    fun getMap(token: String) {
//        viewModelScope.launch {
//            _mapsData.postValue(storyRepository.getMap(token))
//        }
//    }

//    fun getMap(token: String) {
//        val client = ApiConfig.getApiService().getMaps("Bearer $token")
//        client.enqueue(object : Callback<ResponseStory> {
//            override fun onResponse(
//                call: Call<ResponseStory>,
//                response: Response<ResponseStory>
//            ) {
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        _mapsData.value = response.body()?.listStory
//                    }
//                } else {
//                    Log.e(ListStoryActivity.TAG, "onFailure: ${response.message()}")
//                }
//            }
//            override fun onFailure(call: Call<ResponseStory>, t: Throwable) {
//                Log.e(ListStoryActivity.TAG, "onFailure: ${t.message}")
//            }
//        })
//    }
}

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MapsViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}