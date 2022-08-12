package com.ferdibayu.githubuser2.database
//NAMA                : FERDI BAYU FEBRYAN
//NIM                 : 10119246
//KELAS               : IF-06
//TANGGAL PENGERJAAN  :
//DEKRIPSI            : APLIKASI PENCARI USER GITHUB YANG TERVERFIKASI
import android.net.Uri
import android.provider.BaseColumns

object UserContract {
    const val AUTHORITY ="com.ferdibayu.githubuser2"
    const val SCHEME = "content"

    class UserColumns: BaseColumns {
        companion object {
            const val TABLE_NAME = "user"
            const val _ID ="_id"
            const val USERNAME = "username"
            const val AVATAR = "avatar"
            const val URL = "url"

            val Content_Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}