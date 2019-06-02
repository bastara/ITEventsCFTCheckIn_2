package com.example.iteventscftcheck_in

import android.app.Application

import androidx.room.Room

import com.example.iteventscftcheck_in.api.TeamCFTAPI
import com.example.iteventscftcheck_in.db.DatabaseHelper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    var databaseInstance: DatabaseHelper? = null
        private set

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
                .baseUrl(this.getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        api = retrofit.create(TeamCFTAPI::class.java)

        instance = this
        databaseInstance = Room.databaseBuilder(applicationContext, DatabaseHelper::class.java, this.getString(R.string.DB))
                .allowMainThreadQueries()
                .build()
    }

    companion object {

        var api: TeamCFTAPI? = null
            private set

        var instance: App? = null
            private set
    }
}