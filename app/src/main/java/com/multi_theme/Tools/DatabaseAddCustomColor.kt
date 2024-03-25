package com.multi_theme.Tools

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.multi_theme.Item.ItemCustomColor

class DatabaseAddCustomColor(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME (colorQuantity INTEGER PRIMARY KEY AUTOINCREMENT, colorPosition INTEGER, colorBackgroundMainLight TEXT, colorBackgroundMainDark TEXT, colorBackgroundSecondLight TEXT, colorBackgroundSecondDark TEXT, colorBackgroundThirdLight TEXT, colorBackgroundThirdDark TEXT, colorAppBarMain TEXT , colorAppBarSecond TEXT , colorStatusBarMain TEXT , colorStatusBarSecond TEXT , colorNavigationBarMain TEXT , colorNavigationBarSecond TEXT , colorTextMainLight TEXT , colorTextMainDark TEXT , colorTextSecondLight TEXT , colorTextSecondDark TEXT , colorNoActiveLight TEXT , colorNoActiveDark TEXT , colorActiveLight TEXT , colorActiveDark TEXT , colorIconMainLight TEXT , colorIconMainDark TEXT , colorIconSecondLight TEXT , colorIconSecondDark TEXT , colorErrorBackground TEXT , colorErrorText TEXT , colorErrorIcon TEXT , colorAccentBackground TEXT , colorAccentText TEXT , colorAccentIcon TEXT)")
        db.execSQL("create trigger unique_colorPosition AFTER INSERT ON $TABLE_NAME BEGIN UPDATE $TABLE_NAME SET colorPosition = (SELECT MAX(colorPosition) + 1 FROM $TABLE_NAME) WHERE colorQuantity = new.colorQuantity;END")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(" DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    val notes: ArrayList<ItemCustomColor>
        get() {
            val arrayList = ArrayList<ItemCustomColor>()
            val selectQuery = "SELECT *FROM $TABLE_NAME"
            val db = this.writableDatabase
            @SuppressLint("Recycle") val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val itemCustomColor = ItemCustomColor()
                    itemCustomColor.setcolorPosition(cursor.getString(0).toInt())

                    itemCustomColor.setcolorBackgroundMainLight(cursor.getString(1))
                    itemCustomColor.setcolorBackgroundMainDark(cursor.getString(2))

                    itemCustomColor.setcolorBackgroundSecondLight(cursor.getString(3))
                    itemCustomColor.setcolorBackgroundSecondDark(cursor.getString(4))

                    itemCustomColor.setcolorBackgroundThirdLight(cursor.getString(5))
                    itemCustomColor.setcolorBackgroundThirdDark(cursor.getString(6))

                    itemCustomColor.setcolorAppBarMain(cursor.getString(7))
                    itemCustomColor.setcolorAppBarSecond(cursor.getString(8))

                    itemCustomColor.setcolorStatusBarMain(cursor.getString(9))
                    itemCustomColor.setcolorStatusBarSecond(cursor.getString(10))

                    itemCustomColor.setcolorNavigationBarMain(cursor.getString(11))
                    itemCustomColor.setcolorNavigationBarSecond(cursor.getString(12))

                    itemCustomColor.setcolorTextMainLight(cursor.getString(13))
                    itemCustomColor.setcolorTextMainDark(cursor.getString(14))

                    itemCustomColor.setcolorTextSecondLight(cursor.getString(15))
                    itemCustomColor.setcolorTextSecondDark(cursor.getString(16))

                    itemCustomColor.setcolorNoActiveLight(cursor.getString(17))
                    itemCustomColor.setcolorNoActiveDark(cursor.getString(18))

                    itemCustomColor.setcolorActiveLight(cursor.getString(19))
                    itemCustomColor.setcolorActiveDark(cursor.getString(20))

                    itemCustomColor.setcolorIconMainLight(cursor.getString(21))
                    itemCustomColor.setcolorIconMainDark(cursor.getString(22))

                    itemCustomColor.setcolorIconSecondLight(cursor.getString(23))
                    itemCustomColor.setcolorIconSecondDark(cursor.getString(24))

                    itemCustomColor.setcolorErrorBackground(cursor.getString(25))
                    itemCustomColor.setcolorErrorText(cursor.getString(26))
                    itemCustomColor.setcolorErrorIcon(cursor.getString(27))

                    itemCustomColor.setcolorAccentBackground(cursor.getString(28))
                    itemCustomColor.setcolorAccentText(cursor.getString(29))
                    itemCustomColor.setcolorAccentIcon(cursor.getString(30))
                    arrayList.add(itemCustomColor)
                } while (cursor.moveToNext())
            }
            return arrayList
        }

    fun delete(colorQuantity: Int?) {
        val sqLiteDatabase = this.writableDatabase
        sqLiteDatabase.delete(TABLE_NAME, "colorQuantity=$colorQuantity", null)
        sqLiteDatabase.close()
    }

    fun deleteAll() {
        val sqLiteDatabase = this.writableDatabase
        sqLiteDatabase.delete(TABLE_NAME, null, null)
        sqLiteDatabase.close()
    }

    fun editSpecificData(colorQuantity: Int, columnName: String?, name: String?){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(columnName, name)
        db.execSQL("update $TABLE_NAME SET $columnName = '$name' WHERE colorQuantity = '$colorQuantity';")
        db.close()
    }

    fun addCustomColor(
        colorPosition: Int?,
        colorBackgroundMainLight: String?,
        colorBackgroundMainDark: String?,
        colorBackgroundSecondLight: String?,
        colorBackgroundSecondDark: String?,
        colorBackgroundThirdLight: String?,
        colorBackgroundThirdDark: String?,
        colorAppBarMain: String?,
        colorAppBarSecond: String?,
        colorStatusBarMain: String?,
        colorStatusBarSecond: String?,
        colorNavigationBarMain: String?,
        colorNavigationBarSecond: String?,
        colorTextMainLight: String?,
        colorTextMainDark: String?,
        colorTextSecondLight: String?,
        colorTextSecondDark: String?,
        colorNoActiveLight: String?,
        colorNoActiveDark: String?,
        colorActiveLight: String?,
        colorActiveDark: String?,
        colorIconMainLight: String?,
        colorIconMainDark: String?,
        colorIconSecondLight: String?,
        colorIconSecondDark: String?,
        colorErrorBackground: String?,
        colorErrorText: String?,
        colorErrorIcon: String?,
        colorAccentBackground: String?,
        colorAccentText: String?,
        colorAccentIcon: String?,
    ) {
        val sqLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put("colorPosition", colorPosition)

        values.put("colorBackgroundMainLight", colorBackgroundMainLight)
        values.put("colorBackgroundMainDark", colorBackgroundMainDark)

        values.put("colorBackgroundSecondLight", colorBackgroundSecondLight)
        values.put("colorBackgroundSecondDark", colorBackgroundSecondDark)

        values.put("colorBackgroundThirdLight", colorBackgroundThirdLight)
        values.put("colorBackgroundThirdDark", colorBackgroundThirdDark)

        values.put("colorAppBarMain", colorAppBarMain)
        values.put("colorAppBarSecond", colorAppBarSecond)

        values.put("colorStatusBarMain", colorStatusBarMain)
        values.put("colorStatusBarSecond", colorStatusBarSecond)

        values.put("colorNavigationBarMain", colorNavigationBarMain)
        values.put("colorNavigationBarSecond", colorNavigationBarSecond)

        values.put("colorTextMainLight", colorTextMainLight)
        values.put("colorTextMainDark", colorTextMainDark)

        values.put("colorTextSecondLight", colorTextSecondLight)
        values.put("colorTextSecondDark", colorTextSecondDark)

        values.put("colorNoActiveLight", colorNoActiveLight)
        values.put("colorNoActiveDark", colorNoActiveDark)

        values.put("colorActiveLight", colorActiveLight)
        values.put("colorActiveDark", colorActiveDark)

        values.put("colorIconMainLight", colorIconMainLight)
        values.put("colorIconMainDark", colorIconMainDark)

        values.put("colorIconSecondLight", colorIconSecondLight)
        values.put("colorIconSecondDark", colorIconSecondDark)

        values.put("colorErrorBackground", colorErrorBackground)
        values.put("colorErrorText", colorErrorText)
        values.put("colorErrorIcon", colorErrorIcon)

        values.put("colorAccentBackground", colorAccentBackground)
        values.put("colorAccentText", colorAccentText)
        values.put("colorAccentIcon", colorAccentIcon)

        sqLiteDatabase.insert(TABLE_NAME, null, values)
        sqLiteDatabase.close()
    }

    companion object {
        const val DATABASE_NAME = "Multi_Theme"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "CustomColor"
    }
}