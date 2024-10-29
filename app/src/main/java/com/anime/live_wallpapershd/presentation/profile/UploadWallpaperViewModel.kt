package com.anime.live_wallpapershd.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.live_wallpapershd.data.dto.UploadWallpaperResponse
import com.anime.live_wallpapershd.repository.UploadWallpaperRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UploadWallpaperViewModel @Inject constructor(
    private val uploadWallpaperRepo : UploadWallpaperRepo
): ViewModel(){
    private val _isUploading = MutableStateFlow(false)
    val isUploading: StateFlow<Boolean> get() = _isUploading
    val errorTitle = MutableStateFlow<String?>(null)
    val errorCat = MutableStateFlow<String?>(null)
    val errorType = MutableStateFlow<String?>(null)

    private fun handleErrorResponse(jsonString: String) {
        val jsonObject = JSONObject(jsonString)
        val errors = jsonObject.optJSONObject("errors")
        val titleError = errors?.optJSONArray("title")
        val catError = errors?.optJSONArray("category")
        val typeError = errors?.optJSONArray("type")
        errorTitle.value = titleError?.optString(0)
        errorCat.value = catError?.optString(0)
        errorType.value = typeError?.optString(0)
    }

     fun uploadWallpaper(token: String, title: String, catId: Int,type: MultipartBody.Part,  onSuccess: () -> Unit){
         viewModelScope.launch {
             _isUploading.value = true
             val response : Response<UploadWallpaperResponse> = uploadWallpaperRepo.uploadWallpaper(
                 token, title, catId, type
             )
             _isUploading.value = false
             if (response.isSuccessful) {
                 Log.d("Upload", "Successfully upload wallpaper")
                 onSuccess()
             } else {
//                 Log.e("Upload", "Failed to upload wallpaper: ${response.errorBody()?.string()}")
//                 errorMessage.value = response.errorBody()?.string()
                 val errorBody = response.errorBody()?.string()
                 if (errorBody != null) {
                     handleErrorResponse(errorBody)
                 }

             }
         }
     }

}