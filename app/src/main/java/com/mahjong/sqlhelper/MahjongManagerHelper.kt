package com.mahjong.sqlhelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mahjong.import.MahjongManagerImporter

class MahjongManagerHelper(context: Context?, dbName: String?, factory: SQLiteDatabase.CursorFactory?, version:Int) : SQLiteOpenHelper(context,dbName,factory,version) {

    val CREATE_TBL_SQL = "CREATE TABLE " + MahjongManagerImporter.DATABASE_NAME

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}