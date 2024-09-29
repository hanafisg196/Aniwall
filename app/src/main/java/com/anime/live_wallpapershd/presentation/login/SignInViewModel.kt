package com.anime.live_wallpapershd.presentation.login


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.live_wallpapershd.data.dto.GoogleSignInResponse
import com.anime.live_wallpapershd.repository.SingInRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepo: SingInRepo
) : ViewModel() {

    fun signInWithGoogle(idToken: String, onSuccess: (String, Int) -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                val response: Response<GoogleSignInResponse> = signInRepo.pushToken(idToken)
                if (response.isSuccessful) {
                    response.body()?.let { signInResponse ->
                        val token = signInResponse.user.token
                        val userId = signInResponse.user.id

                        Log.i("GoogleSignIn", "Token push successful: $token")
                        Log.i("GoogleSignIn", "User ID push successful: $userId")

                        withContext(Dispatchers.Main) {
                            onSuccess(token, userId) // Pass both token and userId
                        }
                    } ?: run {
                        val error = Exception("Response body is null")
                        Log.e("GoogleSignIn", "Sign-in failed: $error")
                        withContext(Dispatchers.Main) {
                            onError(error)
                        }
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("GoogleSignIn", "Sign-in failed: $errorBody")
                    withContext(Dispatchers.Main) {
                        onError(Exception("Sign-in failed: $errorBody"))
                    }
                }
            } catch (e: Exception) {
                Log.e("GoogleSignIn", "Exception during sign-in: ${e.message}")
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }
    }

    fun logout(onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                signInRepo.clearSession()
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }


}


