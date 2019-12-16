package com.mahjong.constant

// HelperName is used ImportHelperFactory to make Helper.
enum class HelperNameConst {

    /*
        HelperName to make MahjongManagerHelper at ImportHelperFactory.
     */
    MAHJONG_MANAGER_HELPER {
        override fun getHelperName(): String {
            return "MAHJONG_MANAGER_HELPER"
        }
    };

    //各要素の持つHelperNameを取得するabstractメソッド
    abstract fun getHelperName(): String

}