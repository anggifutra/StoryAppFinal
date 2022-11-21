package com.example.storyapp.view.list_story

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapp.data.ListStory
import com.example.storyapp.data.LoadingStateAdapter
import com.example.storyapp.databinding.ActivityListStoryBinding

class ListStoryActivity : AppCompatActivity() {
    private var _binding: ActivityListStoryBinding? = null
    private val binding  get() = _binding!!
    private val storyViewModel: ListStoryViewModel by viewModels {
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
        binding.rvStory.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter{
                adapter.retry()
            }
        )

        adapter.setOnItemClickCallback(object: StoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListStory) {
                val i = Intent(this@ListStoryActivity, DetailActivity::class.java)
                i.putExtra(DetailActivity.EXTRA_ID, data.id)
                startActivity(i)
            }
        })

        val tok = intent.getStringExtra(TOKEN)
        storyViewModel.getStory(tok.toString()).observe(this) {
            adapter.submitData(lifecycle,it)
        }
        val layoutManager = LinearLayoutManager(this)
        binding.rvStory.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvStory.addItemDecoration(itemDecoration)

    }

    companion object {
        const val TOKEN = "Token"
        const val TAG = "ListStoryActivity"
    }
}