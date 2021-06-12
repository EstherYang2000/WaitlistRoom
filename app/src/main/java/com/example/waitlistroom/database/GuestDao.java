package com.example.waitlistroom.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GuestDao {

    @Query("SELECT * FROM guest")
    List<Guest>getAllUsers();

    @Insert
    void insertGuest(Guest...guests);

    @Delete
    void delete(Guest guest);




}
