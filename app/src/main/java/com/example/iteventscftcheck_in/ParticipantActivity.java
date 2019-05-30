package com.example.iteventscftcheck_in;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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

        recyclerView = findViewById(R.id.recycle_view_participant);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ParticipantAdapter adapter = new ParticipantAdapter(ParticipantActivity.this, members);
        adapter.setClickListener(ParticipantActivity.this);
        recyclerView.setAdapter(adapter);


        App.getApi()
           .getMembersId(106)
           .enqueue(new Callback<List<Members>>() {
               @Override
               public void onResponse(Call<List<Members>> call, Response<List<Members>> response) {
                   members.addAll(response.body());
                   recyclerView.getAdapter()
                               .notifyDataSetChanged();
               }


               @Override
               public void onFailure(Call<List<Members>> call, Throwable t) {
                   Toast.makeText(ParticipantActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT)
                        .show();
               }
           });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ParticipantActivity.this,
                PersonalData.class);
        intent.putExtra("id", String.valueOf(position));
        startActivity(intent);
    }
}
