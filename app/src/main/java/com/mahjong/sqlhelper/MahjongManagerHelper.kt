package com.mahjong.sqlhelper

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mahjong.import.MahjongManagerImporter

class MahjongManagerHelper(
    context: Context?
) : SQLiteOpenHelper(context, MahjongManagerImporter.DATABASE_NAME, null, MahjongManagerImporter.DATABASE_VERSION) {

    val CREATE_T01_OPTION =
        "CREATE TABLE T01_OPTION (OptUma1 Integer,OptUma2 Integer,OptUma3 Integer,OptUma4 Integer,OptOka Integer,Optgenten Integer,Optsentenmiman Integer,Opthasuu Integer,Optkioku Integer,MailGoukeiPoint Integer,MailJyuniritu Integer,MailTaikyokusu Integer,MailJyuniKaisu Integer,MailTobiKaisu Integer,MailHeikinten Integer,MailSaikouten Integer,MailAisyo Integer , OptTippu Integer default 0, MailTippu Integer default 1);"

    val CREATE_T02_PLAYER =
        "CREATE TABLE T02_PLAYER (PlayerSEQ Integer,PlayerName TEXT,PlayerDel Integer,PRIMARY KEY(PlayerSEQ ) );"

    val CREATE_T03_SCORE =
        "CREATE TABLE T03_SCORE (ScoreSEQ Integer,ScoreYear Integer,ScoreMonth Integer,ScoreDay Integer,ScoreValid Integer,PRIMARY KEY(ScoreSEQ ) );"

    val CREATE_T04_GAME =
        "CREATE TABLE T04_GAME (ScoreSEQ Integer,GameSEQ Integer,GameBikou TEXT,GameInputDate TEXT,GameValid Integer,PRIMARY KEY(ScoreSEQ,GameSEQ ) );"

    val CREATE_T05_GAMEDETAIL =
        "CREATE TABLE T05_GAMEDETAIL (ScoreSEQ Integer,GameSEQ Integer,GamePlayer Integer,GameJyuni Integer,GameTensu Integer,GamePoint REAL, GameTippu Integer default 0,PRIMARY KEY(ScoreSEQ,GameSEQ,GameJyuni ) );"

    val CREATE_T06_FIRSTMESSAGE =
        "CREATE TABLE T06_FIRSTMESSAGE (GamenID TEXT,VisitCount Integer,Yobi1 Integer,Yobi2 Integer,YobiStr TEXT,PRIMARY KEY(GamenID ) );"

    val CREATE_INDEX_T05_GAMEDETAIL =
        "CREATE INDEX INDEXkey ON T05_GAMEDETAIL (ScoreSEQ,GameSEQ,GamePlayer );"

    val testSql =
        "select player.PlayerName,sum(detail.GamePoint) as point from T04_GAME game inner join T05_GAMEDETAIL detail on game.ScoreSEQ = detail.ScoreSEQ and game.GameSEQ = detail.GameSEQ inner join T02_PLAYER player on player.PlayerSEQ = detail.GamePlayer where game.ScoreSEQ in (select ScoreSEQ from T03_SCORE score where score.ScoreYear = '2019') group by detail.GamePlayer order by point desc;"

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db?.execSQL(CREATE_T01_OPTION)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}