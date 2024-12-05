package com.anime.live_wallpapershd.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.live_wallpapershd.data.dto.NotificationTokenResponse
import com.anime.live_wallpapershd.repository.NotificationRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepo: NotificationRepo
):ViewModel() {
    fun sendNotificationToken(deviceToken : String) {
    viewModelScope.launch {
        val response: Response<NotificationTokenResponse> = notificationRepo.sendToken(deviceToken)
            if (response.isSuccessful){
                Log.d("Device Token", "Device Token Push Succesfully $deviceToken")
            }
       }
    }
}