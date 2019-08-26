package com.mahjong.import

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.mahjong.sqlhelper.ImportHelperFactory
import java.security.AccessControlContext

class MahjongManagerImpoter : Impoter {

    override val helperName: String = "MahjongManagerHelper"

    companion object MahjongManagerData{

        const val DATABASE_NAME: String = "MahjongMgrBkFile"

        const val DATABASE_VERSION = 1;

        const val TABLE_NAME_OPTION = "T01_OPTION"

        const val TABLE_NAME_PLAYER = "T02_PLAYER"

        const val TABLE_NAME_SCORE = "T03_SCORE"

        const val TABLE_NAME_GAME = "T04_GAME"

        const val TABLE_NAME_GAMEDETAIL = "T05_GAMEDETAIL"

        const val TABLE_NAME_FIRSTMESSAGE = "T06_FIRSTMESSAGE"

    }

    override fun import(context: Context?): SQLiteOpenHelper? {
        //return ImportHelperFactory.createHelper(this.helperName,context)
        return null
    }

}