package com.mahjong.main

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mahjong.R
import com.mahjong.import.MahjongManagerImporter


val testSql =
    "select player.PlayerName,sum(detail.GamePoint) as point from T04_GAME game inner join T05_GAMEDETAIL detail on game.ScoreSEQ = detail.ScoreSEQ and game.GameSEQ = detail.GameSEQ inner join T02_PLAYER player on player.PlayerSEQ = detail.GamePlayer where game.ScoreSEQ in (select ScoreSEQ from T03_SCORE score where score.ScoreYear = '2019') group by detail.GamePlayer order by point desc;"

class MainActivity : AppCompatActivity() {

    //SQLite Connection Wrapper
    private var helper : SQLiteOpenHelper? = null

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

         //Import Mahjong Manager
         val cursor = mahjongManagerImport().readableDatabase.rawQuery(testSql,null)

         cursor.moveToFirst()


    }

    private fun mahjongManagerImport(): SQLiteOpenHelper {
         //Set Context at Activity
         return MahjongManagerImporter.import(getApplicationContext())

         //Set Context at Application
         //return MahjongManagerImporter.import(this)
    }
}
