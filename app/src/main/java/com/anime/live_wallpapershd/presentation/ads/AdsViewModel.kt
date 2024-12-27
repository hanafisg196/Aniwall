package com.anime.live_wallpapershd.presentation.ads

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.live_wallpapershd.model.Ads
import com.anime.live_wallpapershd.repository.AdsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdsViewModel @Inject constructor(
    private val repository: AdsRepo
):ViewModel() {
    private val _ads = MutableStateFlow<Ads?>(null)
    val adsState: StateFlow<Ads?> get() = _ads
    private fun getAds(){
        viewModelScope.launch {
         val response = repository.getAds()
            _ads.value = response.data
            Log.d(TAG, "Ads Response: ${response.data.admobInterstitial}")
        }
    }
 init {
     getAds()
 }
}