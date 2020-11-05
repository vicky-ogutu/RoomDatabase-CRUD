package com.example.roomdatabasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Add_student extends AppCompatActivity {
    public static final String EXTRA_FIRSTNAME = "com.example.roomdatabasetutorial.EXTRA_FIRSTNAME";
    public static final String EXTRA_LASTNAME = "com.example.roomdatabasetutorial.EXTRA_LASTNAME";

    EditText first_name, last_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_add);
        setTitle("Add Student");
    }

    private void saveStudent(){
        String firstName = first_name.getText().toString();
        String lastName = last_name.getText().toString();

        if(firstName.trim().isEmpty() || lastName.trim().isEmpty()){

            Toast.makeText(this, "Please enter first name and Last name", Toast.LENGTH_SHORT).show();
            return;

        }

        Intent data = new Intent();
        data.putExtra(EXTRA_FIRSTNAME, firstName);
        data.putExtra(EXTRA_LASTNAME, lastName);

        setResult(RESULT_OK, data);
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.studentmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.save_student:
                saveStudent();
                return true;

            default: return super.onOptionsItemSelected(item);



        }

    }
}
