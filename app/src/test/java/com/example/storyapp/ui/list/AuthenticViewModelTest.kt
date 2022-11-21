package com.example.storyapp.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.storyapp.DataDummy
import com.example.storyapp.MainDispatcherRule
import com.example.storyapp.data.UserLogin
import com.example.storyapp.data.UserPreference
import com.example.storyapp.getOrAwaitValue
import com.example.storyapp.view.authentic.AuthenticViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AuthenticViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var pref: UserPreference
    private lateinit var authenticViewModel: AuthenticViewModel
    private val dummyUser = DataDummy.generateDummyUser()

    @Before
    fun setUp() {
        authenticViewModel = AuthenticViewModel(pref)
    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `when Get User Should Not Null and Return Success`() = runTest {
        val expectedUser = flow<UserLogin> {dummyUser}
        `when`(pref.getUser()).thenReturn(expectedUser)
        val actualUser=  flow<UserLogin> {  authenticViewModel.getUser().getOrAwaitValue()}
        Assert.assertNotNull(actualUser)
    }
}




