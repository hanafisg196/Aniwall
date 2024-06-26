package com.anime.live_wallpapershd.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.live_wallpapershd.model.User
import com.anime.live_wallpapershd.repository.UserProfileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val repository: UserProfileRepo
) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val state: StateFlow<User?> get() = _user

    fun getCurrentUser(token: String) {
        viewModelScope.launch {
            try {
                val user = repository.getUserProfile(token)
                _user.value = user
                Log.d("UserProfileViewModel", "User fetched successfully: $user")
            } catch (e: Exception) {
                Log.e("UserProfileViewModel", "Error fetching user", e)
            }
        }
    }
}
