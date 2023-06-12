package com.example.book_apointments

//class DiseaseDatabase {
//
//
//
//}

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

class DiseaseDatabase(private val dbName: String) {
    private var conn: Connection? = null

    init {
        // Connect to the database
        conn = DriverManager.getConnection("jdbc:sqlite:$dbName")

        // Create the diseases table if it doesn't already exist
        val createTableSQL = "CREATE TABLE IF NOT EXISTS diseases (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "severity INTEGER NOT NULL" +
                ");"
        conn!!.createStatement().execute(createTableSQL)

        // Insert some example data
        val insertDataSQL = "INSERT INTO diseases (name, severity) VALUES " +
                "('Malaria', 3)," +
                "('Cancer', 5)," +
                "('Heart Disease', 4)," +
                "('Diabetes', 2)," +
                "('Flu', 1)," +
                "('Back Pain', 3)," +
                "('Fracture', 4)," +
                "('Sprain', 2)," +
                "('Asthma', 3)," +
                "('Arthritis', 4)," +
                "('Headache', 1)," +
                "('Depression', 5)," +
                "('Anxiety', 4)," +
                "('Allergies', 2)," +
                "('Hypertension', 3);"
        conn!!.createStatement().execute(insertDataSQL)
    }

    fun getDiseases(): List<Pair<String, Int>> {
        val stmt: Statement = conn!!.createStatement()
        val rs = stmt.executeQuery("SELECT name, severity FROM diseases")
        val diseases = mutableListOf<Pair<String, Int>>()
        while (rs.next()) {
            val name = rs.getString("name")
            val severity = rs.getInt("severity")
            diseases.add(Pair(name, severity))
        }
        return diseases
    }
}

