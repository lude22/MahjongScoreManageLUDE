package com.mahjong.jonglog.constant

/**
 * This Class is Enum Class to Define HelperName.
 * HelperName is used ImportHelperFactory to make Helper.
 */
enum class HelperNameConst {

    /**
     * HelperName to make MahjongManagerHelper at ImportHelperFactory.
     */
    MAHJONG_MANAGER_HELPER {
        override fun getHelperName(): String {
            return "MAHJONG_MANAGER_HELPER"
        }
    };

    /**
     * Abstract Method to get HelperName.
     */
    abstract fun getHelperName(): String

}