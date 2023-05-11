package com.example.mob_dev_portfolio.databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReportFormDAO {
    @Query("SELECT * FROM reportform")
    List<ReportForm> getAllReportForms();

    @Query("SELECT * FROM reportform WHERE phone_number = :phoneNo LIMIT 1")
    ReportForm getByPhoneNo(int phoneNo);

    @Insert
    void insertAll(ReportForm... reportForms);

    @Delete
    void delete(ReportForm reportForm);

    @Query("SELECT * FROM reportform ORDER BY id DESC LIMIT 1")
    ReportForm getMostRecentlyAdded();

    @Query("DELETE FROM reportform")
    void deleteAllForm();
}
