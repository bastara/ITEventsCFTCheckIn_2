package com.example.iteventscftcheck_in

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Intent
import android.os.Bundle

import com.example.iteventscftcheck_in.ui.adapter.EventAdapter
import com.example.iteventscftcheck_in.db.model.EventsModel
import com.example.iteventscftcheck_in.model.City
import com.example.iteventscftcheck_in.model.Events
import com.example.iteventscftcheck_in.ui.ParticipantActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Locale

class MainActivity : AppCompatActivity(), EventAdapter.ItemClickListener {
    private lateinit var recyclerView: RecyclerView
    internal lateinit var events: MutableList<Events>

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        events = ArrayList()

        recyclerView = findViewById(R.id.recycle_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val databaseHelper = App.instance?.databaseInstance
        val adapter = databaseHelper?.dataDao?.allData?.let { EventAdapter(it) }
        adapter?.setClickListener(this@MainActivity)
        recyclerView.adapter = adapter

        App.api?.getAllEvents()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe { refreshEvents ->
            events.addAll(refreshEvents)
            if (fillDB()) {
                adapter?.refreshData(databaseHelper.dataDao
                        .allData)
            }
        }
    }

    private fun fillDB(): Boolean {
        val databaseHelper = App.instance?.databaseInstance
        var checkChange = false

        val model = EventsModel()

        for (i in events.indices) {
            model.id = events[i]
                    .id!!

            if (databaseHelper != null) {
                if (databaseHelper.dataDao
                                .getDataById(model.id)
                                .size == 1) {
                    continue
                }
            }

            checkChange = true

            model.name = events[i]
                    .title

            model.date = events[i]
                    .date!!
                    .start?.let { getDateEvent(it) }

            model.city = getCitiesEvent(events[i]
                    .cities!!)

            model.description = events[i]
                    .description

            model.urlBackground = events[i]
                    .cardImage

            databaseHelper!!.dataDao
                    .insertEvents(model)
        }
        return checkChange
    }

    private fun getCitiesEvent(cities: List<City>): String {
        val city = StringBuilder()

        for (j in cities.indices) {
            if (j != 0) {
                city.append("\n")
            }
            city.append(cities[j]
                    .nameRus)
        }
        return city.toString()
    }

    private fun getDateEvent(day: String): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        return DateFormat.getDateInstance(DateFormat.MEDIUM)
                .format(sdf.parse(day))
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this@MainActivity,
                ParticipantActivity::class.java)
        intent.putExtra("id", position.toString())
        startActivity(intent)
    }
}
