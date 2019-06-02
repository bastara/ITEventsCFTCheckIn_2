package com.example.iteventscftcheck_in.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.example.iteventscftcheck_in.db.model.EventsModel
import com.example.iteventscftcheck_in.db.model.ParticipantModel

@Dao
interface DataDao {

    @get:Query("SELECT * FROM EventsModel")
    val allData: List<EventsModel>

    @get:Query("SELECT * FROM ParticipantModel")
    val allDataParticipant: List<ParticipantModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(eventsModel: EventsModel)

    @Query("SELECT * FROM EventsModel WHERE id LIKE :id")
    fun getDataById(id: Int): List<EventsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertParticipant(participantModel: ParticipantModel)

    @Update
    fun update(participantModel: ParticipantModel)

    @Query("SELECT * FROM ParticipantModel WHERE id LIKE :id")
    fun getParticipantById(id: Int): ParticipantModel
}
