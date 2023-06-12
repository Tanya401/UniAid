
package com.example.book_apointments

import AppointmentAdapter
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.book_apointments.databinding.ActivityReqApptsAdminBinding


class ReqAppts_Admin : AppCompatActivity() {

    private lateinit var binding: ActivityReqApptsAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityReqApptsAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUserEmail = "prachibagga"
        val dbHelper = DataBaseHelper(this)
        val appointments = dbHelper.getAppointmentsForEmail(currentUserEmail)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AppointmentAdapter(appointments)
        recyclerView.adapter = adapter
    }
}

