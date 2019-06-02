package com.example.iteventscftcheck_in

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import com.example.iteventscftcheck_in.ui.adapter.EventAdapter
import com.example.iteventscftcheck_in.db.model.EventsModel
import com.example.iteventscftcheck_in.model.City
import com.example.iteventscftcheck_in.model.Events
import com.example.iteventscftcheck_in.ui.ParticipantActivity

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Locale
import java.util.Objects

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), EventAdapter.ItemClickListener {
    private lateinit var recyclerView: RecyclerView
    internal lateinit var events: MutableList<Events>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        events = ArrayList()

        App.getApi()
                .allPosts
                .enqueue(object : Callback<List<Events>> {
                    override fun onResponse(call: Call<List<Events>>, response: Response<List<Events>>) {
                        events.addAll(Objects.requireNonNull<List<Events>>(response.body()))
                        fillDB()
                        recyclerView.adapter?.notifyDataSetChanged()
                    }

                    override fun onFailure(call: Call<List<Events>>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "An error occurred during networking", Toast.LENGTH_SHORT)
                                .show()
                    }
                })

        recyclerView = findViewById(R.id.recycle_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val databaseHelper = App.getInstance()
                .databaseInstance
        val adapter = EventAdapter(databaseHelper.dataDao
                .allData)
        adapter.setClickListener(this@MainActivity)
        recyclerView.adapter = adapter
    }

    private fun fillDB() {
        val databaseHelper = App.getInstance()
                .databaseInstance

        val model = EventsModel()

        for (i in events.indices) {
            model.id = events[i]
                    .id!!

            if (databaseHelper.dataDao
                            .getDataById(model.id)
                            .size == 1) {
                continue
            }

            model.name = events[i]
                    .title

            model.date = events[i]
                    .date?.start?.let { getDateEvent(it) }

            model.city = events[i]
                    .cities?.let { getCitiesEvent(it) }

            model.description = events[i]
                    .description

            model.urlBackground = events[i]
                    .cardImage

            databaseHelper.dataDao
                    .insertEvents(model)
        }
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
        var date: String? = null
        try {
            date = DateFormat.getDateInstance(DateFormat.MEDIUM)
                    .format(sdf.parse(day))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this@MainActivity,
                ParticipantActivity::class.java)
        intent.putExtra("id", position.toString())
        startActivity(intent)
    }
}
