package com.soar.architecture.utils

import android.content.Context
import com.soar.architecture.R
import com.soar.architecture.constants.*


object ErrorMapper {
    fun getErrorString(context: Context, errorId: Int): String? {
        val defaultValue: (key: Int) -> String = { context.getString(R.string.network_error) }
        val mapOf = mapOf(
            Pair(NO_INTERNET_CONNECTION, context.getString(R.string.no_internet)),
            Pair(NETWORK_ERROR, context.getString(R.string.network_error)),
            Pair(PASS_WORD_ERROR, context.getString(R.string.invalid_password)),
            Pair(USER_NAME_ERROR, context.getString(R.string.invalid_username)),
            Pair(CHECK_YOUR_FIELDS, context.getString(R.string.invalid_username_and_password)),
            Pair(SEARCH_ERROR, context.getString(R.string.search_error))
        ).withDefault(defaultValue)
        return mapOf[errorId]
    }
}
