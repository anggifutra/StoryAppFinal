package com.example.storyapp.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.storyapp.DataDummy
import com.example.storyapp.MainDispatcherRule
import com.example.storyapp.data.UserLogin
import com.example.storyapp.data.UserPreference
import com.example.storyapp.getOrAwaitValue
import com.example.storyapp.view.maps.MapsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MapsViewModelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var pref: UserPreference
    private lateinit var mapsViewModel: MapsViewModel
    private val dummyUser = DataDummy.generateDummyUser()

    @Before
    fun setUp() {
        mapsViewModel = MapsViewModel(pref)
    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `when Get User Detail Not Null and Return Success`() = runTest {
        val expectedUser = flow<UserLogin> {dummyUser}
        Mockito.`when`(pref.getUser()).thenReturn(expectedUser)
        flow<UserLogin> {  mapsViewModel.getUser().getOrAwaitValue()}
        val actualMaps = mapsViewModel.getMap("123")
        Assert.assertNotNull(actualMaps)
    }
}