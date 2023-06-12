import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CalculateDatabaseHelper(context: Context) :

    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "calculate_database"
        private const val DATABASE_VERSION = 1

         const val TABLE_NAME = "user_data"
        const val COLUMN_EMAIL_ID = "email_id"
         const val COLUMN_VISITS = "visits"
         const val COLUMN_DISEASES = "diseases"
    const val COLUMN_HEALTH_SCORE = "health_score"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_EMAIL_ID TEXT PRIMARY KEY, " +
                "$COLUMN_VISITS INTEGER DEFAULT 0, " +
                "$COLUMN_DISEASES TEXT, " +
                "$COLUMN_HEALTH_SCORE INTEGER DEFAULT 100)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(query)
        onCreate(db)
    }


   fun getNumberOfVisits(emailId: String): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT visits FROM user_data WHERE email_id = ?", arrayOf(emailId))
        var numOfVisits = 0
        if (cursor.moveToFirst()) {
            numOfVisits = cursor.getInt(cursor.getColumnIndexOrThrow("visits"))
        }
        cursor.close()
        db.close()
        return numOfVisits


        //email id, no of visits
    }

}


