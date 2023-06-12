package com.example.book_apointments

import CalculateDatabaseHelper.Companion.TABLE_NAME
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "myDatabase.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "myTable"
        const val COLUMN_ID = "id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_REASON = "reason"
        const val COLUMN_DATE = "date"
        const val COLUMN_TIME = "time"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_EMAIL TEXT, $COLUMN_REASON TEXT, $COLUMN_DATE TEXT, $COLUMN_TIME TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(dropTable)
        onCreate(db)
    }

    fun getAppointmentsForEmail(email: String): List<Appointments> {
        val appointments = mutableListOf<Appointments>()
        val db = writableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL='$email'", null)
        cursor?.let {
            while (it.moveToNext()) {
                val columnIndex = cursor.getColumnIndex(COLUMN_REASON)
                val reason = if (columnIndex >= 0) cursor.getString(columnIndex) else null
                val dateColumnIndex = cursor.getColumnIndex(COLUMN_DATE)
                val date = if (dateColumnIndex >= 0) cursor.getString(dateColumnIndex) else null

                val timeColumnIndex = cursor.getColumnIndex(COLUMN_TIME)
                val time = if (timeColumnIndex >= 0) cursor.getString(timeColumnIndex) else null

                appointments.add(Appointments(reason, date, time, email))
            }
        }
        cursor?.close()
        db.close()
        return appointments
    }

    fun insertAppointment(appointment: Appointments): Long {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_EMAIL, appointment.email)
            put(COLUMN_REASON, appointment.reason)
            put(COLUMN_DATE, appointment.date)
            put(COLUMN_TIME, appointment.time)
        }
        val result = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return result
    }
}



