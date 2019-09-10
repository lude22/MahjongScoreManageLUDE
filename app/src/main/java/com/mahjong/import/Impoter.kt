package com.mahjong.import

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

interface Impoter {

    val helperName: String

    abstract fun import(context: Context?): SQLiteOpenHelper?

}