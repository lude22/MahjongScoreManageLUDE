package Import

import android.database.sqlite.SQLiteOpenHelper

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

    override fun import(): SQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}