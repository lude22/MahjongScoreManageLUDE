package com.mahjong.jonglog.main


import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahjong.jonglog.R
import kotlinx.android.synthetic.main.activity_main.*
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment
import android.security.keystore.KeyGenParameterSpec
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.security.crypto.MasterKeys
import com.mahjong.jonglog.constant.MahjongManagerConst
import com.mahjong.jonglog.sqlhelper.HelperFactory
import com.mahjong.jonglog.sqlhelper.MahjongManagerHelper


val testSql1 =
    "select player.PlayerName,sum(detail.GamePoint) as point from T04_GAME game inner join T05_GAMEDETAIL detail on game.ScoreSEQ = detail.ScoreSEQ and game.GameSEQ = detail.GameSEQ inner join T02_PLAYER player on player.PlayerSEQ = detail.GamePlayer where game.ScoreSEQ in (select ScoreSEQ from T03_SCORE score where score.ScoreYear = '2019') group by detail.GamePlayer order by point desc;"

val testSql2 = "select * from T05_GAMEDETAIL;"

class MainActivity : AppCompatActivity() {

    //SQLite Connection Wrapper
    private var helper: SQLiteOpenHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v("MainActivity", "start onCreate")

        button.setOnClickListener {

            Log.v("button", "start setOnClickListener")

            //Import Mahjong Manager

            var helper =
                HelperFactory.createHelper(MahjongManagerConst.helperName, applicationContext)

            val cursor: Cursor

            if (helper is MahjongManagerHelper) {
                helper.createDataBase(MahjongManagerConst.COPY_MODE_ASEETS)

                cursor = helper.readableDatabase.rawQuery(testSql1, null)
                Log.v("cursor", cursor.count.toString())

                if (cursor.moveToFirst()) {
                    do {
                        val player = cursor.getString(cursor.getColumnIndex("GamePoint"))
                        Log.v("cursor", "Player : $player")
                    } while (cursor.moveToNext())
                }
            }



            Log.v("button", "end setOnClickListener")

        }

        backup.setOnClickListener {

            Log.v("backup", "start setOnClickListener")


            if (isExternalStorageWritable()) {

                var helper =
                    HelperFactory.createHelper(MahjongManagerConst.helperName, applicationContext)

                val cursor: Cursor

                if (helper is MahjongManagerHelper) {
                    helper.createDataBase(MahjongManagerConst.COPY_MODE_BACKUP)

                    if (helper.getBackUpExist()) {
                        cursor = helper.readableDatabase.rawQuery(testSql2, null)
                        Log.v("cursor", cursor.count.toString())

                        if (cursor.moveToFirst()) {
                            do {
                                val player = cursor.getString(cursor.getColumnIndex("GamePoint"))
                                Log.v("cursor", "Player : $player")
                            } while (cursor.moveToNext())
                        }
                    } else {
                        alertNoBackUpFile()
                    }
                }
            }

            Log.v("backup", "end setOnClickListener")

        }

        Log.v("MainActivity", "end onCreate")
    }

    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun isExternalStorageReadble(): Boolean {
        return Environment.getExternalStorageState() in setOf(
            Environment.MEDIA_MOUNTED,
            Environment.MEDIA_MOUNTED_READ_ONLY
        )
    }

    private fun alertNoBackUpFile() {
        val alert = AlertDialog.Builder(this)
        alert.setMessage("バックアップファイルが存在しないため\n処理を中断します。").setPositiveButton("OK") { _, _ -> }.show()
    }

}
