package com.example.storyapp.view.list_story

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapp.data.UserPreference
import com.example.storyapp.databinding.ActivityListStoryBinding
import com.example.storyapp.view.ViewModelFactory
import com.example.storyapp.view.authentic.AuthenticViewModel

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class ListStoryActivity : AppCompatActivity() {
    private var _binding: ActivityListStoryBinding? = null
    private val binding  get() = _binding!!
    private lateinit var viewModel: AuthenticViewModel
    private val StoryViewModel: ListStoryViewModel by viewModels {
        ViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.title = "Story"
        _binding = ActivityListStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
    }
    private fun getData() {
        val adapter = StoryAdapter()
        binding.rvStory.adapter = adapter

        viewModel = ViewModelProvider(
            this
            , ViewModelFactory(UserPreference.getInstance(dataStore))
        )[AuthenticViewModel::class.java]

        viewModel.getUser().observe(this){
            StoryViewModel.getStory(it.token)
        }
        StoryViewModel.listStory.observe(this) {
            adapter.submitList(it)
        }
        val layoutManager = LinearLayoutManager(this)
        binding.rvStory.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvStory.addItemDecoration(itemDecoration)
        //binding.rvStory.setHasFixedSize(true)

    }

    companion object {
        const val TOKEN = "token"
        const val TAG = "ListStoryActivity"
    }

    //        val listStoryViewModel = ViewModelProvider(
//            this
//            ,ViewModelFactory(UserPreference.getInstance(dataStore))
//        )[ListStoryViewModel::class.java]
//        listStoryViewModel.isLoading.observe(this) {
//            showLoading(it)
//        }
//        listStoryViewModel.getUser().observe(this){
//            listStoryViewModel.getStory(it.token)
//        }
//        listStoryViewModel.listStory.observe(this) { items ->
//            setStoryData(items as ArrayList<ListStory>)
//        }

    //            adapter.setOnItemClickCallback(object: StoryAdapter.OnItemClickCallback {
//                override fun onItemClicked(data: ListStory) {
//                    val i = Intent(this@ListStoryActivity, DetailActivity::class.java)
//                    i.putExtra(DetailActivity.EXTRA_ID, data.id)
//                    startActivity(i)
//                }
//            })

//    private fun setStoryData(items : ArrayList<ListStory>) {
//        val adapter = StoryAdapter(items)
//        adapter.setOnItemClickCallback(object: StoryAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: ListStory) {
//                val i = Intent(this@ListStoryActivity, DetailActivity::class.java)
//                i.putExtra(DetailActivity.EXTRA_ID, data.id)
//                startActivity(i)
//            }
//        })
//        binding.rvStory.adapter = adapter
//
//        val layoutManager = LinearLayoutManager(this)
//        binding.rvStory.layoutManager = layoutManager
//        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
//        binding.rvStory.addItemDecoration(itemDecoration)
//        binding.rvStory.setHasFixedSize(true)
//    }

//    private fun showLoading(isLoading: Boolean) {
//        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//    }


}