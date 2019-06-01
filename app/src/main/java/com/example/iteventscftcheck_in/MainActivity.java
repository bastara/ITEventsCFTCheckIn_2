package com.example.iteventscftcheck_in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.iteventscftcheck_in.db.DatabaseHelper;
import com.example.iteventscftcheck_in.db.model.DataModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements EventAdapter.ItemClickListener {
    RecyclerView recyclerView;
    List<Events> events;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        events = new ArrayList<>();

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = App.getInstance()
                            .getDatabaseInstance();
        EventAdapter adapter = new EventAdapter(this, databaseHelper.getDataDao()
                                                                    .getAllData());
        adapter.setClickListener(MainActivity.this);
        recyclerView.setAdapter(adapter);


        App.getApi()
           .getAllPosts()
           .enqueue(new Callback<List<Events>>() {
               @Override
               public void onResponse(Call<List<Events>> call, Response<List<Events>> response) {
                   events.addAll(response.body());
                   fillDB();
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

    private void fillDB() {
        DatabaseHelper databaseHelper = App.getInstance()
                                           .getDatabaseInstance();

        DataModel model = new DataModel();


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

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            String date = null;
            try {
                date = DateFormat.getDateInstance(DateFormat.MEDIUM)
                                 .format(sdf.parse(events.get(i)
                                                         .getDate()
                                                         .getStart()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            model.setDate(date);

            List<City> listCity = events.get(i)
                                        .getCities();
            String city = "";

            for (int j = 0; j < listCity.size(); j++) {
                if (j != 0) {
                    city += "\n";
                }
                city += listCity.get(j)
                                .getNameRus();
            }
            model.setCity(city);

            model.setDescription(events.get(i)
                                       .getDescription());

            model.setUrlBackground(events.get(i)
                                         .getCardImage());

            databaseHelper.getDataDao()
                          .insert(model);
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this,
                ParticipantActivity.class);
        intent.putExtra("id", String.valueOf(position));
        startActivity(intent);
    }
}
