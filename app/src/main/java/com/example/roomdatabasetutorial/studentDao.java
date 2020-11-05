package com.example.roomdatabasetutorial;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface studentDao {

    @Insert
    void insert(student student);

    @Delete
    void update(student student);

    @Update
    void delete(student student);


    @Query("Delete FROM student_table")
    void deleteAllStudents();

    @Query(" SELECT * FROM student_table")
    LiveData<List<student>> getAllStudents();



}
