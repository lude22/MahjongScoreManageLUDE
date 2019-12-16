package com.mahjong.import

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.mahjong.constant.HelperNameConst
import com.mahjong.sqlhelper.ImportHelperFactory

object MahjongManagerImporter : Impoter {

    override val helperName: String = HelperNameConst.MAHJONG_MANAGER_HELPER.getHelperName()

    const val DATABASE_NAME: String = "MahjongMgrBkFile"

    const val DATABASE_VERSION = 1;

    const val TABLE_NAME_OPTION = "T01_OPTION"

    const val TABLE_NAME_PLAYER = "T02_PLAYER"

    const val TABLE_NAME_SCORE = "T03_SCORE"

    const val TABLE_NAME_GAME = "T04_GAME"

    const val TABLE_NAME_GAMEDETAIL = "T05_GAMEDETAIL"

    const val TABLE_NAME_FIRSTMESSAGE = "T06_FIRSTMESSAGE"

    var helper : SQLiteOpenHelper? = null

    override fun import(context: Context?): SQLiteOpenHelper? {
        this.helper = ImportHelperFactory.createHelper(this.helperName,context)

        return helper
    }

}