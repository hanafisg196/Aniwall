package com.anisuki.animewallpapers.presentation.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anisuki.animewallpapers.common.Resource
import com.anisuki.animewallpapers.domain.usecases.RandomUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomListViewModel @Inject constructor(
    private val getRandomUseCases : RandomUseCases
):ViewModel() {
    private val _state = mutableStateOf(RandomListState())
    val state: State<RandomListState> = _state

    init {
        getRandom()
    }
    private fun getRandom()
    {
        viewModelScope.launch {
            getRandomUseCases().onEach { result ->
                when(result)
                {
                    is Resource.Success -> {
                        _state.value = RandomListState(random = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = RandomListState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is  Resource.Loading -> {
                        _state.value = RandomListState(isLoading = true)
                    }
                }

            }.launchIn(viewModelScope)
        }

    }
}