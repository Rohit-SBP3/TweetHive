package com.example.tweethive.repository

import android.util.Log
import com.example.tweethive.api.TweetHiveAPI
import com.example.tweethive.model.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetHiveAPI: TweetHiveAPI){

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> get() = _categories

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>> get() = _tweets

    suspend fun getCategories(){
        val response = tweetHiveAPI.getCategories()
        if(response.isSuccessful && response.body() != null){
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String){
        val response = tweetHiveAPI.getTweets("tweets[?(@.category==\"$category\")]")
        if(response.isSuccessful && response.body() != null){
            _tweets.emit(response.body()!!)
        }else {
            Log.e("TweetRepository", "Error fetching tweets: ${response.code()} ${response.message()}")
        }
    }
}