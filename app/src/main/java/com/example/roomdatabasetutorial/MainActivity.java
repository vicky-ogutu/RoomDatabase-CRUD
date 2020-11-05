package com.example.roomdatabasetutorial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    private studentViewmodel studentViewmode2;
    public static final int ADD_STUDENT_REQUEST=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fb = findViewById(R.id.addfab);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, Add_student.class);
                startActivityForResult(intent,1);
            }
        });

        recyclerView1 = findViewById(R.id.recyclerView);
        final studentAdapter adapter = new studentAdapter();
        recyclerView1.setAdapter(adapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        studentViewmode2 = ViewModelProviders.of(this).get(studentViewmodel.class);
        studentViewmode2.get_AllStudents().observe(this, new Observer<List<student>>() {
            @Override
            public void onChanged(List<student> students) {
                adapter.set_students(students);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_STUDENT_REQUEST  && resultCode== RESULT_OK){
            String firstname = data.getStringExtra(Add_student.EXTRA_FIRSTNAME);
            String lastname = data.getStringExtra(Add_student.EXTRA_LASTNAME);

            student student = new student(firstname, lastname);
            studentViewmode2.insert(student);
            Toast.makeText(this, "student saved", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(this, "not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
