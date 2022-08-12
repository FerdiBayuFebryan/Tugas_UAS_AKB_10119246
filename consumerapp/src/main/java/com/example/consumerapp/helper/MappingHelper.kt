package com.example.consumerapp.helper

import android.database.Cursor
import com.example.consumerapp.database.UserContract.UserColumns.Companion.AVATAR
import com.example.consumerapp.database.UserContract.UserColumns.Companion.URL
import com.example.consumerapp.database.UserContract.UserColumns.Companion.USERNAME
import com.example.consumerapp.database.UserContract.UserColumns.Companion._ID
import com.example.consumerapp.model.User

object MappingHelper {
    fun mapCursorToArrayList(usersCursor: Cursor?): ArrayList<User> {
        val userList = ArrayList<User>()

        usersCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(_ID))
                val username = getString(getColumnIndexOrThrow(USERNAME))
                val avatar = getString(getColumnIndexOrThrow(AVATAR))
                val url = getString(getColumnIndexOrThrow(URL))
                userList.add(User(id, username, avatar, url))
            }
        }
        return userList
    }
}