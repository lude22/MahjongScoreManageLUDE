package Import

import android.database.sqlite.SQLiteOpenHelper

interface Impoter {

    val helperName: String

    fun import(): SQLiteOpenHelper

}