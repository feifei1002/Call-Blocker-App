package com.example.mob_dev_portfolio.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {BlockList.class}, version = 1)
public abstract class BlockListDatabase extends RoomDatabase {
    public abstract BlockListDAO blockListDAO();
}