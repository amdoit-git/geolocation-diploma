package ru.practicum.android.diploma.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // TODO("Not yet implemented")
    }
}
