package com.example.simplegolf.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EntityDAO {
    @Query("SELECT * FROM testentity")
    List<TestEntity> getAll();

    @Query("SELECT * FROM testentity WHERE uid IN (:userIds)")
    List<TestEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM testentity WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    TestEntity findByName(String first, String last);

    @Insert
    void insertAll(TestEntity... users);

    @Delete
    void delete(TestEntity user);
}
