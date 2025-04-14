package com.esm.taskify_news.domain.usecases.app_entry

import com.esm.taskify_news.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow


class ReadAppEntry(
    private val localUserManger: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }

}