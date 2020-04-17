package com.mahjong.jonglog.sqlhelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment
import android.util.Log
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.mahjong.jonglog.constant.DataBaseNameConst
import com.mahjong.jonglog.constant.MahjongManagerConst
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


/**
 * 麻雀マネージャーのバックアップファイルをインポートするクラス。
 * 対象ファイル：MahjongMgrBkFile
 */
class MahjongManagerHelper : SQLiteOpenHelper {

    /**
     * コンストラクタ。
     */
    constructor(context: Context) : super(
        context,
        MahjongManagerConst.DATABASE_NAME,
        null,
        MahjongManagerConst.DATABASE_VERSION
    ) {
        mContext = context
        dbPath = mContext.getDatabasePath(databaseName).absolutePath
        Log.v("MahjongManagerHelper", "dpPath : $dbPath")
    }

    private var mContext: Context

    private var dbPath: String

    private var backUpExist: Boolean = true

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
    public fun createDataBase(copyMode: Int) {

        Log.v("MahjongManagerHelper", "start createDataBase")

        //データベースの存在判定
        if (!checkDBExists()) {
            // readableDatabaseメソッドを呼び出すことで、DBがオープンされる
            readableDatabase

            var toCheckDb: SQLiteDatabase? = null

            try {
                copyDataBase(copyMode)

                toCheckDb = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY)

            } catch (e: IOException) {
                e.run { e.printStackTrace() }
            } catch (e: SQLiteException) {
                e.run { e.printStackTrace() }
            } finally {
                toCheckDb?.close()
            }

        }

        Log.v("MahjongManagerHelper", "end createDataBase")
    }

    /**
     * データベースの存在判定を行う。
     * 既に存在する場合はTRUE、それ以外はFALSE。
     */
    private fun checkDBExists(): Boolean {

        Log.v("MahjongManagerHelper", "start checkDBExists")

        var db: SQLiteDatabase? = null

        try {
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY)
        } catch (e: SQLiteException) {
            e.run { printStackTrace() }
        }

        if (db == null) {
            // データベースが存在しない
            Log.v("MahjongManagerHelper", "reason:There is no DB.")
            Log.v("MahjongManagerHelper", "end checkDBExists")
            return false
        }

        // データベースのバージョンチェック
        if (db.version == MahjongManagerConst.DATABASE_VERSION) {
            // データベースのバージョンが最新
            db.close()
            Log.v("MahjongManagerHelper", "reason:DB version is latest.")
            Log.v("MahjongManagerHelper", "end checkDBExists")
            return true
        }

        // データベースのバーションが最新でないため削除
        val dbFile = File(dbPath)
        dbFile.delete()
        Log.v("MahjongManagerHelper", "reason:DB version isn't latest.")
        Log.v("MahjongManagerHelper", "end checkDBExists")
        return false
    }

    /**
     * 麻雀マネージャーのDBファイルを雀ログのDBファイルへコピーする。
     */
    private fun copyDataBase(copyMode: Int) {

        Log.v("MahjongManagerHelper", "start copyDataBase")

        when (copyMode) {
            MahjongManagerConst.COPY_MODE_ASEETS -> copyDataBaseFromAseets()

            MahjongManagerConst.COPY_MODE_BACKUP -> copyDataBaseFromBackUp()
        }

        Log.v("MahjongManagerHelper", "end copyDataBase")
    }

    private fun copyDataBaseFromAseets() {

        Log.v("MahjongManagerHelper", "start copyDataBaseFromAseets")

        var inputStream: InputStream =
            mContext.assets.open(DataBaseNameConst.MAHJONG_MANAGER.getDataBaseName())
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

        Log.v("MahjongManagerHelper", "end copyDataBaseFromAseets")

    }

    private fun copyDataBaseFromBackUp() {

        Log.v("MahjongManagerHelper", "start copyDataBaseFromBackUp")

        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterkeysAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        val backupFile = File(
            mContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
            databaseName
        )

        // backup用のファイルが存在しない場合、処理終了
        if (!backupFile.exists()) {
            backUpExist = backupFile.exists()
            Log.v("MahjongManagerHelper", "There is no backup file.")
            Log.v("MahjongManagerHelper", "end copyDataBaseFromBackUp")
            return
        }

        // バックアップからコピーする場合、dbPath上のDBファイルを削除し、新規にファイルを生成する

        mContext.deleteFile(databaseName)

        val inputEncryptedFile = EncryptedFile.Builder(
            backupFile,
            mContext,
            masterkeysAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        val contents = inputEncryptedFile.openFileInput().bufferedReader().useLines { lines ->
            lines.fold("") { working, line ->
                "$working\n$line"
            }
        }

        val outputEncryptedFile = EncryptedFile.Builder(
            File(dbPath, databaseName),
            mContext,
            masterkeysAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        outputEncryptedFile.openFileOutput().bufferedWriter().use { writer ->
            writer.write(contents)
        }

        Log.v("MahjongManagerHelper", "end copyDataBaseFromBackUp")

    }

    public fun getBackUpExist():Boolean {
        return backUpExist
    }

}