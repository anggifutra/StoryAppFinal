package com.example.storyapp.view.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.storyapp.data.UserLogin
import com.example.storyapp.data.UserPreference
import kotlinx.coroutines.launch

class WelcomeViewModel(private val pref: UserPreference): ViewModel() {

    fun getUser(): LiveData<UserLogin> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}