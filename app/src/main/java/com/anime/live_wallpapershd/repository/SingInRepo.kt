package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.data.dto.GoogleSignInResponse
import com.anime.live_wallpapershd.data.request.SingInRequest
import com.anime.live_wallpapershd.model.User
import com.pixplicity.easyprefs.library.Prefs
import retrofit2.Response
import javax.inject.Inject

class SingInRepo @Inject constructor(
    private val api : ApiService
) {
    suspend fun pushToken(idToken: String): Response<GoogleSignInResponse> {
        val signInRequest = SingInRequest(idToken)
        return api.googleSignIn(signInRequest)
    }

    fun clearSession() {
        Prefs.remove("google_id_token")
        Prefs.remove("token_auth")
    }
}