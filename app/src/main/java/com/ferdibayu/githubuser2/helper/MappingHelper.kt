package com.ferdibayu.githubuser2.helper
//NAMA                : FERDI BAYU FEBRYAN
//NIM                 : 10119246
//KELAS               : IF-06
//TANGGAL PENGERJAAN  :
//DEKRIPSI            : APLIKASI PENCARI USER GITHUB YANG TERVERFIKASI
import android.database.Cursor
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.AVATAR
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.URL
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.USERNAME
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion._ID
import com.ferdibayu.githubuser2.model.User

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