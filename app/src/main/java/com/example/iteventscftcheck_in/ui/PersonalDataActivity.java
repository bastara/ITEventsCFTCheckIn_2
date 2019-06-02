package com.example.iteventscftcheck_in.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iteventscftcheck_in.App;
import com.example.iteventscftcheck_in.R;
import com.example.iteventscftcheck_in.db.DatabaseHelper;

public class PersonalDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_data);

        TextView firstName = findViewById(R.id.tv_first_name);
        TextView lastName = findViewById(R.id.tv_last_name);
        TextView phone = findViewById(R.id.tv_phone);
        TextView email = findViewById(R.id.tv_email);
        TextView city = findViewById(R.id.tv_city);

        Intent intent = getIntent();
        int position = Integer.parseInt(intent.getStringExtra("id"));

        DatabaseHelper databaseHelper = App.getInstance()
                                           .getDatabaseInstance();
        firstName.setText(databaseHelper.getDataDao()
                                        .getAllDataParticipant()
                                        .get(position)
                                        .getFirstName());

        lastName.setText(databaseHelper.getDataDao()
                                       .getAllDataParticipant()
                                       .get(position)
                                       .getLastName());

        phone.setText(databaseHelper.getDataDao()
                                    .getAllDataParticipant()
                                    .get(position)
                                    .getPhone());

        email.setText(databaseHelper.getDataDao()
                                    .getAllDataParticipant()
                                    .get(position)
                                    .getEmail());

        city.setText(databaseHelper.getDataDao()
                                   .getAllDataParticipant()
                                   .get(position)
                                   .getCity());
    }
}
