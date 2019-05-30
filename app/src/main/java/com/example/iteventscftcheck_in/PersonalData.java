package com.example.iteventscftcheck_in;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class PersonalData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_data);

        TextView firstName = findViewById(R.id.tv_first_name);
        TextView lastName = findViewById(R.id.tv_last_name);
        TextView phone = findViewById(R.id.tv_phone);
        TextView email = findViewById(R.id.tv_email);
        TextView city = findViewById(R.id.tv_city);

    }
}
