package com.esm.taskify_news.domain.usecases.app_entry

import com.esm.taskify_news.domain.manager.LocalUserManager

class SaveAppEntry (private val localUserManager: LocalUserManager) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }

}