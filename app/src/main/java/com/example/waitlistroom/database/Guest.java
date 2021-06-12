package com.example.waitlistroom.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Guest {

    @PrimaryKey(autoGenerate = true )
    public int Gid;
    @ColumnInfo(name="Guest_name")
    public String GuestName;
    @ColumnInfo(name="party_size")
    public String Partysize;

}
