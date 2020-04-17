package com.mahjong.jonglog.sqlhelper

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.mahjong.jonglog.constant.HelperNameConst

object HelperFactory {

    //Create SQLiteHelper from helperName
    fun createHelper(helperName: String, context: Context): SQLiteOpenHelper? {

        Log.v("HelperFactory", "start createHelper")

        when (helperName) {
            HelperNameConst.MAHJONG_MANAGER_HELPER.getHelperName() -> {
                Log.v("HelperFactory", "end createHelper")
                return MahjongManagerHelper(context)
            }
        }

        return null
    }
}