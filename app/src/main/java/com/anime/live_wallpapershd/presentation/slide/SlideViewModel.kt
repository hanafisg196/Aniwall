package com.anime.live_wallpapershd.presentation.slide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.live_wallpapershd.model.Slide
import com.anime.live_wallpapershd.repository.SlideRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideViewModel @Inject constructor(
    private val slideRepo: SlideRepo

): ViewModel(){
    private val _state = MutableStateFlow(emptyList<Slide>())
    val state: StateFlow<List<Slide>>
        get() = _state
    init {
        viewModelScope.launch {
            val slides = slideRepo.getSlide()
            _state.value = slides
        }
    }

}