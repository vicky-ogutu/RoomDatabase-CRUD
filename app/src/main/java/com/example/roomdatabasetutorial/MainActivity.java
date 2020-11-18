package com.example.roomdatabasetutorial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
                startActivityForResult(intent,ADD_STUDENT_REQUEST);
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
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

              studentViewmode2.delete(adapter.getStudentAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "student deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView1);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.delete_all:
                studentViewmode2.deleteAll();
                Toast.makeText(this, "All students deleted", Toast.LENGTH_SHORT).show();
            default: return super.onOptionsItemSelected(item);
        }

    }
}
