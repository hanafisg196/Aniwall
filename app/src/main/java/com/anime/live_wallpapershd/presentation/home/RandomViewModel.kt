package com.anime.live_wallpapershd.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.live_wallpapershd.model.Random
import com.anime.live_wallpapershd.repository.RandomRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RandomViewModel @Inject constructor(
    private val randomRepo: RandomRepo
): ViewModel() {
    private val _state = MutableStateFlow(emptyList<Random>())
    val state: StateFlow<List<Random>>
        get() = _state
    init {
        viewModelScope.launch {
            val randoms = randomRepo.getRandoms()
            _state.value = randoms
        }
    }

}



