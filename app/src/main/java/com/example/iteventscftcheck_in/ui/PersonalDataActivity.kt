package com.example.iteventscftcheck_in.ui

import android.os.Bundle
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import com.example.iteventscftcheck_in.App
import com.example.iteventscftcheck_in.R

class PersonalDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_data)

        val firstName = findViewById<TextView>(R.id.tv_first_name)
        val lastName = findViewById<TextView>(R.id.tv_last_name)
        val phone = findViewById<TextView>(R.id.tv_phone)
        val email = findViewById<TextView>(R.id.tv_email)
        val city = findViewById<TextView>(R.id.tv_city)

        val intent = intent
        val position = Integer.parseInt(intent.getStringExtra("id"))

        val databaseHelper = App.instance?.databaseInstance
        if (databaseHelper != null) {
            firstName.text = databaseHelper.dataDao
                    .allDataParticipant[position]
                    .firstName

            lastName.text = databaseHelper.dataDao
                    .allDataParticipant[position]
                    .lastName

            phone.text = databaseHelper.dataDao
                    .allDataParticipant[position]
                    .phone

            email.text = databaseHelper.dataDao
                    .allDataParticipant[position]
                    .email

            city.text = databaseHelper.dataDao
                    .allDataParticipant[position]
                    .city
        }
    }
}