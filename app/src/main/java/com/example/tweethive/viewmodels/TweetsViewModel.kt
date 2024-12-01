package com.example.tweethive.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweethive.model.TweetListItem
import com.example.tweethive.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(
    private val repository: TweetRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val tweets: StateFlow<List<TweetListItem>> get() = repository.tweets

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "Android"
            repository.getTweets(category)
        }
    }

}