package com.anime.live_wallpapershd.services

import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject


fun validatedHandler(
    jsonString: String,
    errorMap: Map<String, MutableStateFlow<String?>>)
{
    val jsonObject = JSONObject(jsonString)
    val errors = jsonObject.optJSONObject("errors") ?: return
    for((field, errorState) in errorMap){
            val errorCollection = errors.optJSONArray(field)
            errorState.value = errorCollection?.optString(0)
    }

}