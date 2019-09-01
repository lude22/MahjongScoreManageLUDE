package com.mahjong.common

// HelperName is used ImportHelperFactory to make Helper.
enum class HelperNameConst {
    MAHJONG_MANAGER_HELPER {
        override fun getString(): String {
            return "MAHJONG_MANAGER_HELPER"
        }
    };

    abstract fun getString(): String

}