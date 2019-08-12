package SQLiteOpenHelper

import Import.MahjongManagerImpoter
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

object HelperFactory {

    fun createHelper(helperName: String,context: Context): SQLiteOpenHelper? {
        if(helperName == "MahjongManagerHelper"){
            return MahjongManagerHelper(context,MahjongManagerImpoter.DATABASE_NAME,null,MahjongManagerImpoter.DATABASE_VERSION)
        }
        return null
    }
}