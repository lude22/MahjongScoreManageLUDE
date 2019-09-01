package com.mahjong.sqlhelper

import com.mahjong.import.MahjongManagerImpoter
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.support.constraint.solver.widgets.Helper
import com.mahjong.common.HelperNameConst

object ImportHelperFactory {

    fun createHelper(helperName: String, context: Context): SQLiteOpenHelper? {

        when (helperName) {
            HelperNameConst.MAHJONG_MANAGER_HELPER.getString() -> return MahjongManagerHelper(
                context,
                MahjongManagerImpoter.DATABASE_NAME,
                null,
                MahjongManagerImpoter.DATABASE_VERSION
            )

        }

        return null
    }
}