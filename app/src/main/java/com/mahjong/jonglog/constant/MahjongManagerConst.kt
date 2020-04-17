package com.mahjong.jonglog.constant

/**
 * This class is Object to Define MahjongManager's Info.
 */
object MahjongManagerConst {

    const val DATABASE_VERSION = 1;

    const val TABLE_NAME_OPTION = "T01_OPTION"

    const val TABLE_NAME_PLAYER = "T02_PLAYER"

    const val TABLE_NAME_SCORE = "T03_SCORE"

    const val TABLE_NAME_GAME = "T04_GAME"

    const val TABLE_NAME_GAMEDETAIL = "T05_GAMEDETAIL"

    const val TABLE_NAME_FIRSTMESSAGE = "T06_FIRSTMESSAGE"

    val helperName: String = HelperNameConst.MAHJONG_MANAGER_HELPER.getHelperName()

    val DATABASE_NAME: String = DataBaseNameConst.MAHJONG_MANAGER.getDataBaseName()

    const val COPY_MODE_ASEETS = 1

    const val COPY_MODE_BACKUP = 2

}