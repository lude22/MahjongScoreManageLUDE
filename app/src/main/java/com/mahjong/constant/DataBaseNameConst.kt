package com.mahjong.constant

/**
 * This Class is Enum Class to Define DataBaseName.
 */
enum class DataBaseNameConst {

    MAHJONG_MANAGER {
        override fun getDataBaseName(): String {
            return "MahjongMgrBkFile"
        }
    },
    JONG_LOG{
        override fun getDataBaseName(): String{
            return "JongLogDB"
        }
    };

    /**
     * Abstract Method to get HelperName.
     */
    abstract fun getDataBaseName():String

}