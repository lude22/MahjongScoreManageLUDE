package com.mahjong.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mahjong.R
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.mahjong.constant.MahjongManagerConst
import com.mahjong.sqlhelper.HelperFactory
import com.mahjong.sqlhelper.MahjongManagerHelper


val testSql1 =
    "select player.PlayerName,sum(detail.GamePoint) as point from T04_GAME game inner join T05_GAMEDETAIL detail on game.ScoreSEQ = detail.ScoreSEQ and game.GameSEQ = detail.GameSEQ inner join T02_PLAYER player on player.PlayerSEQ = detail.GamePlayer where game.ScoreSEQ in (select ScoreSEQ from T03_SCORE score where score.ScoreYear = '2019') group by detail.GamePlayer order by point desc;"

val testSql2 = "select * from T02_PLAYER;"

class MainActivity : AppCompatActivity() {

    //SQLite Connection Wrapper
    private var helper: SQLiteOpenHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v("MainActivity", "start onCreate")

        button.setOnClickListener {

            Log.v("PUSH_START", "start setOnClickListener")

            //Import Mahjong Manager

            var helper =
                HelperFactory.createHelper(MahjongManagerConst.DATABASE_NAME, applicationContext)

            val cursor: Cursor

            if (helper is MahjongManagerHelper) {
                helper.createDataBase()

                cursor = helper.readableDatabase.rawQuery(testSql2, null)
                Log.v("cursor", cursor.count.toString())

                if (cursor.moveToFirst()) {
                    do {
                        val player = cursor.getString(cursor.getColumnIndex("PlayerName"))
                        Log.v("cursor", "Player : $player")
                    } while (cursor.moveToNext())
                }
            }

            Log.v("PUSH_END", "end setOnClickListener")

        }

    }

}
