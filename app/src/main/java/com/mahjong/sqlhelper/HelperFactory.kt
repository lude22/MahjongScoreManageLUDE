package com.mahjong.sqlhelper

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.mahjong.constant.HelperNameConst

object HelperFactory {

    //Create SQLiteHelper from helperName
    fun createHelper(helperName: String, context: Context): SQLiteOpenHelper? {

        when (helperName) {
            HelperNameConst.MAHJONG_MANAGER_HELPER.getHelperName() -> return MahjongManagerHelper(
                context
            )

        }

        return null
    }
}