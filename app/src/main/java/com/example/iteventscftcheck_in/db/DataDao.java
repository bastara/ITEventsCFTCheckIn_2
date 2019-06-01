package com.example.iteventscftcheck_in.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.iteventscftcheck_in.db.model.EventsModel;
import com.example.iteventscftcheck_in.db.model.ParticipantModel;

import java.util.List;

@Dao
public interface DataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvents(EventsModel eventsModel);

    @Delete
    void delete(EventsModel eventsModel);

    @Query("SELECT * FROM EventsModel")
    List<EventsModel> getAllData();

    @Query("SELECT * FROM EventsModel WHERE id LIKE :id")
    List<EventsModel> getDataById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertParticipant(ParticipantModel participantModel);

    @Update
    void update(ParticipantModel participantModel);

    @Query("SELECT * FROM ParticipantModel")
    List<ParticipantModel> getAllDataParticipant();

    @Query("SELECT * FROM ParticipantModel WHERE id LIKE :id")
    ParticipantModel getParticipantById(int id);
}
