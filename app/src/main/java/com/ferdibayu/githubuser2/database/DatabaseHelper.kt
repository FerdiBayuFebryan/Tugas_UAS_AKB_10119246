package com.ferdibayu.githubuser2.database
//NAMA                : FERDI BAYU FEBRYAN
//NIM                 : 10119246
//KELAS               : IF-06
//TANGGAL PENGERJAAN  :
//DEKRIPSI            : APLIKASI PENCARI USER GITHUB YANG TERVERFIKASI
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.AVATAR
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.TABLE_NAME
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.URL
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.USERNAME
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion._ID

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "dbuserapp"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_USER = "CREATE TABLE $TABLE_NAME" +
                "($_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$USERNAME TEXT NOT NULL," +
                "$AVATAR TEXT NOT NULL," +
                "$URL TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}