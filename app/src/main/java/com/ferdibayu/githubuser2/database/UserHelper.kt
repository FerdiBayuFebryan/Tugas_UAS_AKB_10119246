package com.ferdibayu.githubuser2.database
//NAMA                : FERDI BAYU FEBRYAN
//NIM                 : 10119246
//KELAS               : IF-06
//TANGGAL PENGERJAAN  :
//DEKRIPSI            : APLIKASI PENCARI USER GITHUB YANG TERVERFIKASI
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.TABLE_NAME
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion._ID

class UserHelper(context: Context) {

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var databaseHelper: DatabaseHelper

        private var TAG = UserHelper::class.simpleName

        private var INSTANCE: UserHelper? = null
        fun getInstance(context: Context): UserHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserHelper(context)
            }

        private lateinit var database: SQLiteDatabase
    }

    init {
        databaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLiteException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()
        if (database.isOpen) database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"
        )
    }

    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    fun delete(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
    }

    fun check(id: String): Boolean {
        database = databaseHelper.writableDatabase
        val selectId = "SELECT * FROM $DATABASE_TABLE WHERE $_ID =?"
        val cursor = database.rawQuery(selectId, arrayOf(id))
        var check = false

        if (cursor.moveToFirst()) {
            check = true
            var i = 0
            while (cursor.moveToNext()) {
                i++
            }
            Log.d(TAG, String.format("%d records found", i))
        }
        cursor.close()
        return check
    }

}