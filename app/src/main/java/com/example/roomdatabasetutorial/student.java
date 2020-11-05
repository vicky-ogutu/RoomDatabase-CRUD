package com.example.roomdatabasetutorial;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class student {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String First_name;
    private  String Last_name;

    public student(String First_name, String Last_name) {
       this.First_name = First_name;
       this.Last_name = Last_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return First_name;
    }

    public String getLast_name() {
        return Last_name;
    }


}
