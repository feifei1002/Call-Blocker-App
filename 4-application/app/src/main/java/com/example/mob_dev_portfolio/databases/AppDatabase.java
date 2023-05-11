package com.example.mob_dev_portfolio.databases;

import androidx.room.RoomDatabase;
import androidx.room.Database;

@Database(entities = {ReportForm.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReportFormDAO reportFormDAO();
}
