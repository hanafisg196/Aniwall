
package com.anime.live_wallpapershd.presentation.detail
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.live_wallpapershd.data.dto.FavoriteResponse
import com.anime.live_wallpapershd.repository.FavoriteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepo: FavoriteRepo
) : ViewModel() {

    private val _checkFavorite = MutableStateFlow(false)
    val checkFavorite: StateFlow<Boolean> get() = _checkFavorite

    fun addFavorite(token: String, wallpaperId: Int) {
        viewModelScope.launch {
            val response: Response<FavoriteResponse> = favoriteRepo.addFavorite(token, wallpaperId)
            if (response.isSuccessful) {
                checkFavorite(token,wallpaperId)
                Log.d("AddFavorite", "Successfully added wallpaper ID: $wallpaperId to favorites")
            }
        }
    }
     fun removeFavorite(token: String, wallpaperId: Int) {
        viewModelScope.launch {
            val response: Response<FavoriteResponse> = favoriteRepo.removeFavorite(token, wallpaperId)
            if (response.isSuccessful) {
                checkFavorite(token,wallpaperId)
                Log.d("RemoveFavorite", "Successfully removed wallpaper ID: $wallpaperId from favorites")
            }
        }
    }

    fun checkFavorite(token: String, wallpaperId: Int) {
        viewModelScope.launch {
            val result = favoriteRepo.checkFavorite(token, wallpaperId)
            _checkFavorite.value = result.isFavorite
        }
    }




}