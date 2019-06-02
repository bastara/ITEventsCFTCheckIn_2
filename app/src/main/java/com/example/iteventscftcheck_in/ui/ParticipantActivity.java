package com.example.iteventscftcheck_in.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iteventscftcheck_in.App;
import com.example.iteventscftcheck_in.R;
import com.example.iteventscftcheck_in.ui.adapter.ParticipantAdapter;
import com.example.iteventscftcheck_in.db.DatabaseHelper;
import com.example.iteventscftcheck_in.db.model.ParticipantModel;
import com.example.iteventscftcheck_in.model.Members;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParticipantActivity extends AppCompatActivity implements ParticipantAdapter.ItemClickListener {

    RecyclerView recyclerView;
    List<Members> members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);

        members = new ArrayList<>();

        App.getApi()
           .getMembersId(106)
           .enqueue(new Callback<List<Members>>() {
               @Override
               public void onResponse(@NonNull Call<List<Members>> call, @NonNull Response<List<Members>> response) {
                   members.addAll(Objects.requireNonNull(response.body()));
                   fillDB();
//                   recyclerView.getAdapter()
//                               .notifyDataSetChanged();
               }

               @Override
               public void onFailure(@NonNull Call<List<Members>> call, @NonNull Throwable t) {
                   Toast.makeText(ParticipantActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT)
                        .show();
               }
           });

        recyclerView = findViewById(R.id.recycle_view_participant);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper databaseHelper = App.getInstance()
                                           .getDatabaseInstance();
        ParticipantAdapter adapter = new ParticipantAdapter(databaseHelper.getDataDao()
                                                                          .getAllDataParticipant());
        adapter.setClickListener(ParticipantActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void fillDB() {
        DatabaseHelper databaseHelper = App.getInstance()
                                           .getDatabaseInstance();

        ParticipantModel model = new ParticipantModel();

        for (int i = 0; i < members.size(); i++) {
            model.setId(members.get(i)
                               .getId());

            if (databaseHelper.getDataDao()
                              .getParticipantById(model.getId())
                    != null) {

                continue;
            }

            model.setFirstName(members.get(i)
                                      .getFirstName());
            model.setLastName(members.get(i)
                                     .getLastName());
            model.setPhone(members.get(i)
                                  .getPhone());
            model.setEmail(members.get(i)
                                  .getEmail());
            model.setCity(members.get(i)
                                 .getCity());
            model.setVisited(members.get(i)
                                    .getIsVisited());
            databaseHelper.getDataDao()
                          .insertParticipant(model);
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ParticipantActivity.this,
                PersonalDataActivity.class);
        intent.putExtra("id", String.valueOf(position));
        startActivity(intent);
    }
}
