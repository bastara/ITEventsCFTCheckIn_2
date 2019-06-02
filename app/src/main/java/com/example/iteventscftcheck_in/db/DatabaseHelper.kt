package com.example.iteventscftcheck_in.db


import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.iteventscftcheck_in.db.model.ParticipantModel
import com.example.iteventscftcheck_in.db.model.EventsModel

@Database(entities = [EventsModel::class, ParticipantModel::class], version = 1, exportSchema = false)
abstract class DatabaseHelper : RoomDatabase() {

    abstract val dataDao: DataDao
}