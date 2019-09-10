package com.mahjong.sqlhelper

import com.mahjong.import.MahjongManagerImporter
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.mahjong.common.HelperNameConst

object ImportHelperFactory {

    //
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