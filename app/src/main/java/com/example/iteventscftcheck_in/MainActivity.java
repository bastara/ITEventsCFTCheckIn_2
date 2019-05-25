package com.example.iteventscftcheck_in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class MainActivity extends AppCompatActivity {
//
//    private ImageView mImageView;
//    private String mImageAddress =
//            "http://developer.alexanderklimov.ru/android/images/android_cat.jpg";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test1);
//
//        mImageView = findViewById(R.id.icon);
//    }
//
//    public void onClick(View view) {
//        // Загружаем картинку
//        Glide
//                .with(this)
//                .load(mImageAddress)
//                .into(mImageView);
//    }
//}


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Events> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        events = new ArrayList<>();

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EventAdapter adapter = new EventAdapter(this, events);
        recyclerView.setAdapter(adapter);

        App.getApi()
           .getAllPosts()
           .enqueue(new Callback<List<Events>>() {
               @Override
               public void onResponse(Call<List<Events>> call, Response<List<Events>> response) {
                   events.addAll(response.body());
                   recyclerView.getAdapter()
                               .notifyDataSetChanged();
               }

               @Override
               public void onFailure(Call<List<Events>> call, Throwable t) {
                   Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT)
                        .show();
               }
           });
    }
}
