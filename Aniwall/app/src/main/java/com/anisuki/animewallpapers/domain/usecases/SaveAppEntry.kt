package com.anisuki.animewallpapers.domain.usecases

import com.anisuki.animewallpapers.domain.manager.LocalUserManager

class SaveAppEntry (
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke()
    {
        localUserManager.saveAppEntry()
    }
}