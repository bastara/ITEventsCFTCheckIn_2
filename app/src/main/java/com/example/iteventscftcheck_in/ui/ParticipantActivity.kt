package com.example.iteventscftcheck_in.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.iteventscftcheck_in.App
import com.example.iteventscftcheck_in.R
import com.example.iteventscftcheck_in.ui.adapter.ParticipantAdapter
import com.example.iteventscftcheck_in.db.model.ParticipantModel
import com.example.iteventscftcheck_in.model.Members
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import java.util.ArrayList

class ParticipantActivity : AppCompatActivity(), ParticipantAdapter.ItemClickListener {

    private lateinit var recyclerView: RecyclerView
    internal lateinit var members: MutableList<Members>

    @SuppressLint("CheckResult")
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

        App.api?.getMembersIdRX(106)?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe { refreshMembers ->
            members.addAll(refreshMembers)
            if (fillDB()) {
                adapter.refreshData(databaseHelper.dataDao
                        .allDataParticipant)
            }
        }
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
