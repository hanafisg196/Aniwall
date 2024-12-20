package com.anime.live_wallpapershd.presentation.report

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.live_wallpapershd.data.dto.MessageResponse
import com.anime.live_wallpapershd.helper.validatedHandler
import com.anime.live_wallpapershd.repository.ReportRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val reportRepo: ReportRepo
): ViewModel() {
    private val _isSendRequest = MutableStateFlow(false)
    val isSendRequest: StateFlow<Boolean> get() = _isSendRequest
    val errorEmail = MutableStateFlow<String?>(null)
    val errorDescription = MutableStateFlow<String?>(null)
    private val errorMessage = mapOf(
        "reporter_email" to errorEmail,
        "description" to errorDescription
    )

    private fun validated(jsonString: String) {
        validatedHandler(jsonString, errorMessage)
    }

    fun sendWallpaperReport(
        wallpaperId: Int,
        email: String,
        description: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _isSendRequest.value = true
            val response: Response<MessageResponse> = reportRepo.sendReportWallpaper(
                wallpaperId, email, description
            )
            _isSendRequest.value = false
            if (response.isSuccessful) {
                onSuccess()
                Log.d("Send Report", "Successfully send report wallpaper")
            } else {
                val errorBody = response.errorBody()?.string()
                if (errorBody != null) {
                    validated(errorBody)
                }
//                Log.e("Send Report Error", "Error code: ${response.code()}, " +
//                "message: ${response.message()}, errorBody: ${response.errorBody()?.string()}")
            }
        }
    }

    fun sendUserReport(userId: Int, email: String, description: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isSendRequest.value = true
            val response: Response<MessageResponse> = reportRepo.sendReportUser(
                userId, email, description
            )
            _isSendRequest.value = false
            if (response.isSuccessful) {
                onSuccess()
                Log.d("Send Report", "Successfully send report user")
            } else {
                val errorBody = response.errorBody()?.string()
                if (errorBody != null) {
                    validated(errorBody)
                }
                 Log.e("Send Report Error", "Error code: ${response.code()}, " +
                "message: ${response.message()}, errorBody: ${response.errorBody()?.string()}")
            }
        }
    }
}