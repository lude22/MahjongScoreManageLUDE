package Import

import android.database.sqlite.SQLiteOpenHelper

interface Impoter {

    fun import(): SQLiteOpenHelper

}