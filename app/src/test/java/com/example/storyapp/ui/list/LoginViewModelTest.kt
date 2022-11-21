package com.example.storyapp.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.storyapp.DataDummy
import com.example.storyapp.MainDispatcherRule
import com.example.storyapp.data.UserPreference
import com.example.storyapp.view.login.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var pref: UserPreference
    private lateinit var loginViewModel: LoginViewModel
    private val dummyUser = DataDummy.generateDummyUser()

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(pref)
    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `when Save User Should Not Null and Return Success`() = runTest {
        val expectedUser = dummyUser
        loginViewModel.saveUser(expectedUser)
        val login = loginViewModel.login("","")
        Assert.assertNotNull(login)
    }
}