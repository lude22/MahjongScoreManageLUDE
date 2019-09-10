package com.mahjong.main

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mahjong.R
import com.mahjong.import.Impoter
import com.mahjong.import.MahjongManagerImporter

class MainActivity : AppCompatActivity() {

    //SQLiteの接続ラッパー
    private var helper : SQLiteOpenHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //麻雀マネージャーをインポート
        helper = mahjongManagerImport()

    }

    private fun mahjongManagerImport(): SQLiteOpenHelper? {
        //コンテキストにActivityをセット
        return MahjongManagerImporter.import(getApplicationContext())

        //コンテキストにApplicationをセット
        //return MahjongManagerImporter.import(this)
    }
}
