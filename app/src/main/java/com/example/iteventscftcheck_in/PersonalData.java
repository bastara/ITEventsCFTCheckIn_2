package com.example.iteventscftcheck_in;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iteventscftcheck_in.db.DatabaseHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalData extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

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

        databaseHelper = App.getInstance()
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
