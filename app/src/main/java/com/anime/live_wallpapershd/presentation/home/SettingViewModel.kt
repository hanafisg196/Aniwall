package com.anime.live_wallpapershd.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.live_wallpapershd.model.Setting
import com.anime.live_wallpapershd.repository.SettingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepo: SettingRepo
):ViewModel() {
    private val  _setting = MutableStateFlow<Setting?>(null)
    val settingState: MutableStateFlow<Setting?> = _setting
    
    private fun getSetting(){
          viewModelScope.launch {
              val setting = settingRepo.setting()
              _setting.value = setting.data
          }

    }
    init {
        getSetting()
    }

}