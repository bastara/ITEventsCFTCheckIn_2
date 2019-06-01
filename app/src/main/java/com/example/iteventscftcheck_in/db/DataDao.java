package com.example.iteventscftcheck_in.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.iteventscftcheck_in.db.model.EventsModel;
import com.example.iteventscftcheck_in.db.model.ParticipantModel;

import java.util.List;

@Dao
public interface DataDao {
    @Insert
    void insertEvents(EventsModel eventsModel);

    @Delete
    void delete(EventsModel eventsModel);

    @Query("SELECT * FROM EventsModel")
    List<EventsModel> getAllData();

    @Query("SELECT * FROM EventsModel WHERE id LIKE :id")
    List<EventsModel> getDataById(int id);

    @Insert
    void insertParticipant(ParticipantModel participantModel);

    @Query("SELECT * FROM ParticipantModel")
    List<ParticipantModel> getAllDataParticipant();

    @Query("SELECT * FROM ParticipantModel WHERE id LIKE :id")
    List<ParticipantModel> getParticipantById(int id);
}
