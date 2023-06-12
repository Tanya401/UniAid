package com.example.book_apointments

import CalculateDatabaseHelper
import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.book_apointments.databinding.ActivityLandingpageBinding

class Landingpage : AppCompatActivity() {
    private lateinit var dbHelper: DataBaseHelper
    private lateinit var binding: ActivityLandingpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setting up healthcore
//        setUpHealthScore()
    }

//    private fun setUpHealthScore() {
//        // Get the previous health score from the database
//        val dbHelper = CalculateDatabaseHelper(this)
//        val db = dbHelper.readableDatabase
//        val projection = arrayOf(CalculateDatabaseHelper.COLUMN_HEALTH_SCORE)
//        val selection = "${CalculateDatabaseHelper.COLUMN_EMAIL_ID} = ?"
//        val sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
//        val email = sharedPreferences.getString("email", "") ?: ""
//        val selectionArgs = arrayOf(email)
//        val cursor = db.query(
//            CalculateDatabaseHelper.TABLE_NAME,
//            projection,
//            selection,
//            selectionArgs,
//            null,
//            null,
//            null
//        )
//        cursor.moveToFirst()
//        val previousHealthScore = cursor.getDouble(cursor.getColumnIndexOrThrow(CalculateDatabaseHelper.COLUMN_HEALTH_SCORE))
//        cursor.close()
//
//        // Get the number of visits and severity of diseases from the respective classes
//        val numberOfVisits = dbHelper.getNumberOfVisits(email)
//        val severityOfDisease = DiseaseDatabase.getSeverity(email)
//
//        // Calculate the new health score
//        val newHealthScore = previousHealthScore - (numberOfVisits * severityOfDisease)
//
//        // Update the health score in the database
//        val values = ContentValues().apply {
//            put(CalculateDatabaseHelper.COLUMN_HEALTH_SCORE, newHealthScore)
//        }
//        val updateSelection = "${CalculateDatabaseHelper.COLUMN_EMAIL_ID} = ?"
//        val updateSelectionArgs = arrayOf(email)
//        db.update(CalculateDatabaseHelper.TABLE_NAME, values, updateSelection, updateSelectionArgs)
//
//        return newHealthScore
//
//    }
}