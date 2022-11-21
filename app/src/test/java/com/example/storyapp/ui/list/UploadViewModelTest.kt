package com.example.storyapp.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.storyapp.DataDummy
import com.example.storyapp.MainDispatcherRule
import com.example.storyapp.data.UserLogin
import com.example.storyapp.data.UserPreference
import com.example.storyapp.getOrAwaitValue
import com.example.storyapp.view.upload.UploadViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UploadViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var pref: UserPreference
    private lateinit var uploadViewModel: UploadViewModel
    private val dummyUser = DataDummy.generateDummyUser()

    @Before
    fun setUp() {
        uploadViewModel = UploadViewModel(pref)
    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `when Get User Detail Not Null and Return Success`() = runTest {
        val expectedUser = flow<UserLogin> {dummyUser}
        Mockito.`when`(pref.getUser()).thenReturn(expectedUser)
        flow<UserLogin> {  uploadViewModel.getUser().getOrAwaitValue()}
        val file = File("currentPhotoPath")
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part =MultipartBody.Part.createFormData(
        "photo",
        "file.name",
        requestImageFile
        )
        val upload = uploadViewModel.upload("123",imageMultipart,"")
        Assert.assertNotNull(upload)
    }
}