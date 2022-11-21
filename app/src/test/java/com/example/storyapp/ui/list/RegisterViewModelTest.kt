package com.example.storyapp.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.storyapp.view.register.RegisterViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class RegisterViewModelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var registerViewModel: RegisterViewModel

    @Before
    fun setUp() {
        registerViewModel = RegisterViewModel()
    }

    @Test
    fun `when Register Return Success`() {
        val register = registerViewModel.register("1","2","2")
        Assert.assertNotNull(register)

    }

}