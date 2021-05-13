package com.a13hay.sqliteapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.ContentView
import androidx.annotation.RequiresApi

var DataBaseName = "spinnerExample"
var DataBaseVersion = 1
var TABLE_NAME = "lables"
var COLUMN_ID = "id"
var COLUMN_NAME = "name"

class DataBaseHandler(context: Context?) : SQLiteOpenHelper(context, DataBaseName, null, DataBaseVersion) {
    override fun onCreate(p0: SQLiteDatabase?) {
        var CREATE_ITEM_TABLE =
            "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY,$COLUMN_NAME TEXT)"
        p0?.execSQL(CREATE_ITEM_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    fun insertLable(lable: String) {
        var db:SQLiteDatabase = this.readableDatabase
        var contentValues:ContentValues = ContentValues()
        contentValues.put(COLUMN_NAME,lable)
        db.insert(TABLE_NAME,null,contentValues)
        db.close()
    }

    fun getAllLables(): List<String> {
        var list = ArrayList<String>()
        var stringQuery = "SELECT * FROM "+TABLE_NAME
        var db:SQLiteDatabase = this.readableDatabase
        var cursor:Cursor = db.rawQuery(stringQuery,null)
        if(cursor.moveToFirst()){
            do {
                list.add(cursor.getString(1))
            }while(cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }
}