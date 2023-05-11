package com.example.mob_dev_portfolio.databases;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BlockListDAO {

    @Query("SELECT * FROM blocklist")
    List<BlockList> getAllBlockList();

    @Query("SELECT * FROM blocklist WHERE phone_no = :phoneNo")
    boolean getBlockListByPhoneNo(String phoneNo);

    @Insert
    void insertAll(BlockList... blockLists);

    @Query("DELETE FROM blocklist")
    void deleteAll();
}
