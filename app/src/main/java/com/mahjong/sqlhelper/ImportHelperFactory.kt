package com.mahjong.sqlhelper

import com.mahjong.import.MahjongManagerImporter
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.mahjong.constant.HelperNameConst

object ImportHelperFactory {

    //Create SQLiteHelper from helperName
    fun createHelper(helperName: String, context: Context?): SQLiteOpenHelper? {

        when (helperName) {
            HelperNameConst.MAHJONG_MANAGER_HELPER.getHelperName() -> return MahjongManagerHelper(
                context,
                MahjongManagerImporter.DATABASE_NAME,
                null,
                MahjongManagerImporter.DATABASE_VERSION
            )

        }

        return null
    }
}