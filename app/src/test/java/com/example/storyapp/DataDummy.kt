package com.example.storyapp

import com.example.storyapp.data.ListStory
import com.example.storyapp.data.UserLogin

object DataDummy {
    fun generateDummyListStory(): List<ListStory> {
        val items: MutableList<ListStory> = arrayListOf()
        for (i in 0..100) {
            val quote = ListStory(
                i.toString(),
                "$i",
                "description $i",
                "https://story-api.dicoding.dev/images/stories/photos-1668678672345_d3ULffqF.jpg",
                i.toDouble(),
                i.toDouble()
            )
            items.add(quote)
        }
        return items
    }
    fun generateDummyUser(): UserLogin {
        return UserLogin(
            "anggi",
            "123",
            true
        )
    }
}