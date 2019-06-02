package com.example.iteventscftcheck_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.iteventscftcheck_in.ui.adapter.EventAdapter;
import com.example.iteventscftcheck_in.db.DatabaseHelper;
import com.example.iteventscftcheck_in.db.model.EventsModel;
import com.example.iteventscftcheck_in.model.City;
import com.example.iteventscftcheck_in.model.Events;
import com.example.iteventscftcheck_in.ui.ParticipantActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements EventAdapter.ItemClickListener {
    RecyclerView recyclerView;
    List<Events> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        events = new ArrayList<>();

        App.getApi()
           .getAllPosts()
           .enqueue(new Callback<List<Events>>() {
               @Override
               public void onResponse(@NonNull Call<List<Events>> call, @NonNull Response<List<Events>> response) {
                   events.addAll(Objects.requireNonNull(response.body()));
                   fillDB();
//                   recyclerView.getAdapter()
//                               .notifyDataSetChanged();
               }

               @Override
               public void onFailure(@NonNull Call<List<Events>> call, @NonNull Throwable t) {
                   Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT)
                        .show();
               }
           });

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper databaseHelper = App.getInstance()
                                           .getDatabaseInstance();
        EventAdapter adapter = new EventAdapter(databaseHelper.getDataDao()
                                                              .getAllData());
        adapter.setClickListener(MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void fillDB() {
        DatabaseHelper databaseHelper = App.getInstance()
                                           .getDatabaseInstance();

        EventsModel model = new EventsModel();

        for (int i = 0; i < events.size(); i++) {
            model.setId(events.get(i)
                              .getId());

            if (databaseHelper.getDataDao()
                              .getDataById(model.getId())
                              .size() == 1) {
                continue;
            }

            model.setName(events.get(i)
                                .getTitle());

            model.setDate(getDateEvent(events.get(i)
                                             .getDate()
                                             .getStart()));

            model.setCity(getCitiesEvent(events.get(i)
                                               .getCities()));

            model.setDescription(events.get(i)
                                       .getDescription());

            model.setUrlBackground(events.get(i)
                                         .getCardImage());

            databaseHelper.getDataDao()
                          .insertEvents(model);
        }
    }

    private String getCitiesEvent(List<City> cities) {

        StringBuilder city = new StringBuilder();

        for (int j = 0; j < cities.size(); j++) {
            if (j != 0) {
                city.append("\n");
            }
            city.append(cities.get(j)
                              .getNameRus());
        }
        return cities.toString();
    }

    private String getDateEvent(String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String date = null;
        try {
            date = DateFormat.getDateInstance(DateFormat.MEDIUM)
                             .format(sdf.parse(day));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this,
                ParticipantActivity.class);
        intent.putExtra("id", String.valueOf(position));
        startActivity(intent);
    }
}
