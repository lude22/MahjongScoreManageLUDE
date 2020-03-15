package com.mahjong.sqlhelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.mahjong.constant.DataBaseNameConst
import com.mahjong.constant.MahjongManagerConst
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * 麻雀マネージャーのバックアップファイルをインポートするクラス。
 * 対象ファイル：MahjongMgrBkFile
 */
class MahjongManagerHelper : SQLiteOpenHelper {

    /**
     * コンストラクタ。
     * コンテキストの保持、
     */
    constructor(context: Context) : super(
        context,
        MahjongManagerConst.DATABASE_NAME,
        null,
        MahjongManagerConst.DATABASE_VERSION
    ) {
        mContext = context
        dbPath = mContext.getDatabasePath(MahjongManagerConst.DATABASE_NAME).absolutePath
    }

    private var mContext: Context

    private var dbPath: String

    override fun onCreate(db: SQLiteDatabase?) {
        //何もしない
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //何もしない
    }

    /**
     * 現存するバックアップファイルをコピーして
     * 麻雀マネージャー用のデータベースを作成する。
     */
    public fun createDataBase() {
        //データベースの存在判定
        if (!checkDBExists()) {
            readableDatabase

            var toCheckDb: SQLiteDatabase? = null

            try {
                copyDataBase()

                toCheckDb = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY)

            } catch (e: IOException) {
                e.run { e.printStackTrace() }
            } catch (e: SQLiteException) {
                e.run { e.printStackTrace() }
            }finally {
                toCheckDb?.close()
            }

        }
    }

    /**
     * データベースの存在判定を行う。
     * 既に存在する場合はTRUE、それ以外はFALSE。
     */
    private fun checkDBExists(): Boolean {

        var db: SQLiteDatabase? = null

        try {
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY)
        } catch (e: SQLiteException) {
            e.run { printStackTrace() }
        }

        if (db == null) {
            // データベースが存在しない
            return false
        }

        // データベースのバージョンチェック
        val oldVersion = db.version
        val newVersion = MahjongManagerConst.DATABASE_VERSION

        if (oldVersion == newVersion) {
            // データベースのバージョンが最新
            db.close()
            return true
        }

        // データベースのバーションが最新でないため削除
        val dbFile = File(dbPath)
        dbFile.delete()
        return false
    }

    /**
     * 麻雀マネージャーのDBファイルを雀ログのDBファイルへコピーする。
     */
    private fun copyDataBase() {
        val inputStream = mContext.assets.open(DataBaseNameConst.MAHJONG_MANAGER.getDataBaseName())
        val outputStream = FileOutputStream(dbPath)

        var buffer: ByteArray = ByteArray(1024)
        var size: Int
        do {
            size = inputStream.read(buffer)

            if (size > 0) {
                outputStream.write(buffer, 0, size)
            }
        } while (size > 0)

        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }

}