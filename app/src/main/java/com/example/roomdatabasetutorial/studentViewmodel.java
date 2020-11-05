package com.example.roomdatabasetutorial;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class studentViewmodel extends AndroidViewModel {
    private studentRepository _studentRepository;
    LiveData<List<student>> _AllStudents;

    public studentViewmodel(@NonNull Application application) {
        super(application);
        _studentRepository = new studentRepository(application);
         _AllStudents = _studentRepository.get_AllStudents();
    }


    LiveData<List<student>> getAllWords() {
        return _AllStudents;
    }

    public void insert(student _student) {
        _studentRepository.insert(_student);
    }

    public void update(student _student) {
        _studentRepository.update(_student);
    }
    public void delete(student _student) {
        _studentRepository.delete(_student);
    }

    public void deleteAll() {
        _studentRepository.deleteAllStudents();
    }

    public LiveData<List<student>> get_AllStudents(){
        return _AllStudents;

    }
}
