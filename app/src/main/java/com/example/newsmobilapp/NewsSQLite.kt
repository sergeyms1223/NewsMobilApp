package com.example.newsmobilapp
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class NewsSQLite(context: Context)://MainActivity) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "news_DataBase.bd"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "news"
        private const val COLUMN_ID = "id"
        private const val COLUMN_ID_NEWS = "id_news"
        private const val COLUMN_TYPE = "type"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_IMAGE = "img"
        private const val COLUMN_NEWS_DATE = "news_date"
        private const val COLUMN_NEWS_DATE_UTS = "news_date_uts"
        private const val COLUMN_ANNOTATION = "annotation"
        private const val COLUMN_MOBILE_URL = "mobile_url"
    }

    private val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
            "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$COLUMN_ID_NEWS INTEGER," +
            "$COLUMN_TYPE TEXT," +
            "$COLUMN_TITLE TEXT," +
            "$COLUMN_IMAGE TEXT," +
            "$COLUMN_NEWS_DATE TEXT," +
            "$COLUMN_NEWS_DATE_UTS TEXT," +
            "$COLUMN_ANNOTATION TEXT," +
            "$COLUMN_MOBILE_URL TEXT" +
            ")"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun clearBD() {
        writableDatabase.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        writableDatabase.execSQL(createTableQuery)
    }

    fun insertNews(
        id_news: Int,
        type: String,
        title: String,
        image: String,
        news_date: String,
        date_news_uts: String,
        annotation: String,
        mobile_url: String
    ) {
        val values = ContentValues()
        values.put(COLUMN_ID_NEWS, id_news)
        values.put(COLUMN_TYPE, type)
        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_IMAGE, image)
        values.put(COLUMN_NEWS_DATE, news_date)
        values.put(COLUMN_NEWS_DATE_UTS, date_news_uts)
        values.put(COLUMN_ANNOTATION, annotation)
        values.put(COLUMN_MOBILE_URL, mobile_url)

        writableDatabase.insert(TABLE_NAME, null, values)
    }

    fun getAllNews() {
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = readableDatabase.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val id_news = cursor.getInt(1)
                val type = cursor.getString(2)
                val title = cursor.getString(3)
                val image = cursor.getString(4)
                val news_date = cursor.getString(5)
                val date_news_uts = cursor.getString(6)
                val annotation = cursor.getString(7)
                val mobile_url = cursor.getString(8)

                Log.d(
                    "БД: ",
                    "$id.) "+
                        "$id_news " +
                        "$type " +
                        "(($title)) " +
                        "$image " +
                        "$news_date  " +
                        "$date_news_uts  " +
                        "(($annotation)) " +
                        "$mobile_url "
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    fun getElement(){

    }
}


