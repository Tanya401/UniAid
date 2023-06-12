package com.example.book_apointments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import com.example.book_apointments.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper:DataBaseHelper
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DataBaseHelper(this)

        binding.selectdate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val currentYear = calendar.get(Calendar.YEAR)
            val currentMonth = calendar.get(Calendar.MONTH)
            val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    binding.selectdate.text = selectedDate
                },
                currentYear,
                currentMonth,
                currentDay
            )
            val minDate = calendar.timeInMillis
            datePickerDialog.datePicker.minDate = minDate
            datePickerDialog.show()
        }

        binding.selecttime.setOnClickListener{
            //time picking dialog
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
                    // Set the time chosen by the user in the button text
                    val chosenTime = String.format("%02d:%02d", hourOfDay, minute)
                    binding.selecttime.text = chosenTime
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }

        binding.book.setOnClickListener{
            val date = binding.selectdate.text.toString()
            val time = binding.selecttime.text.toString()
            val reason = binding.reasontext.text.toString()
            saveToDataBase(date,time,reason)
            Toast.makeText(this,"Appointment confirmed",Toast.LENGTH_SHORT).show()
        }
    }

//    @SuppressLint("SuspiciousIndentation")
    private fun saveToDataBase(date: String, time: String, reason: String) {
     val db = dbHelper.writableDatabase
        db?.let{
            val sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
            val email = sharedPreferences.getString("email", "") ?: ""
            val values = ContentValues().apply{
                put(DataBaseHelper.COLUMN_EMAIL,email)
                put(DataBaseHelper.COLUMN_REASON,reason)
                put(DataBaseHelper.COLUMN_DATE,date)
                put(DataBaseHelper.COLUMN_TIME,time)
            }
            it.insert(DataBaseHelper.TABLE_NAME,null,values)
            it.close()
        }
    }
}