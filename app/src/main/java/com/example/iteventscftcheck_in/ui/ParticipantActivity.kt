package com.example.iteventscftcheck_in.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.iteventscftcheck_in.App
import com.example.iteventscftcheck_in.R
import com.example.iteventscftcheck_in.ui.adapter.ParticipantAdapter
import com.example.iteventscftcheck_in.db.model.ParticipantModel
import com.example.iteventscftcheck_in.model.Members

import java.util.ArrayList
import java.util.Objects

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParticipantActivity : AppCompatActivity(), ParticipantAdapter.ItemClickListener {

    private lateinit var recyclerView: RecyclerView
    internal lateinit var members: MutableList<Members>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participant)

        members = ArrayList()

        recyclerView = findViewById(R.id.recycle_view_participant)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val databaseHelper = App.instance?.databaseInstance
        val adapter = ParticipantAdapter(databaseHelper!!.dataDao.allDataParticipant)
        adapter.setClickListener(this@ParticipantActivity)
        recyclerView.adapter = adapter

        App.api?.getMembersId(106)?.enqueue(object : Callback<List<Members>> {
            override fun onResponse(call: Call<List<Members>>, response: Response<List<Members>>) {
                members.addAll(Objects.requireNonNull<List<Members>>(response.body()))
                if (fillDB()) {
                    adapter.refreshData(databaseHelper.dataDao
                            .allDataParticipant)
                }
            }

            override fun onFailure(call: Call<List<Members>>, t: Throwable) {
                Toast.makeText(this@ParticipantActivity, "An error occurred during networking", Toast.LENGTH_SHORT)
                        .show()
            }
        })
    }

    private fun fillDB(): Boolean {
        val databaseHelper = App.instance?.databaseInstance
        var checkChange = false

        if (databaseHelper != null) {
            val model = ParticipantModel()

            for (i in members.indices) {
                model.id = members[i]
                        .id!!

                @Suppress("SENSELESS_COMPARISON")
                if (databaseHelper.dataDao
                                .getParticipantById(model.id) != null) {
                    continue
                }

                checkChange = true

                model.firstName = members[i]
                        .firstName
                model.lastName = members[i]
                        .lastName
                model.phone = members[i]
                        .phone
                model.email = members[i]
                        .email
                model.city = members[i]
                        .city
                model.isVisited = members[i]
                        .getIsVisited()!!
                databaseHelper.dataDao
                        .insertParticipant(model)
            }
        }
        return checkChange
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this@ParticipantActivity,
                PersonalDataActivity::class.java)
        intent.putExtra("id", position.toString())
        startActivity(intent)
    }
}
