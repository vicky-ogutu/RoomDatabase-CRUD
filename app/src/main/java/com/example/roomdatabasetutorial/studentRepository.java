package com.example.roomdatabasetutorial;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class studentRepository {

    private studentDao _studentDao;
    private LiveData<List<student>> _AllStudents;

        studentRepository(Application application){

        studentRoomDatabase db = studentRoomDatabase.getDatabase(application);
        _studentDao = db._studentDao();
        _AllStudents =_studentDao.getAllStudents();
    }
    //LiveData<List<student>> getAllStudents() {
      //  return _AllStudents;
    //}

    public void insert(student _student){
        new insertAyncTask(_studentDao).execute(_student);

    }
    public void update(student _student){
            new updateAyncTask(_studentDao).execute(_student);


    }
    public void delete(student _student){
            new deleteAyncTask(_studentDao).execute(_student);

    }

    public void deleteAllStudents(){
            new deleteAllAyncTask(_studentDao).execute();

    }

    public LiveData<List<student>> get_AllStudents(){
            return _AllStudents;
    }


    //insert AsyncTask
    private static class insertAyncTask extends AsyncTask< student, Void, Void>{

    private  studentDao mAsyncTaskDao;

        public insertAyncTask(studentDao mDao) {
            mAsyncTaskDao = mDao;
        }

        @Override
        protected Void doInBackground(student... students) {
            mAsyncTaskDao.insert(students[0]);
            return null;
        }
    }
    //update Asynctask
    private static class updateAyncTask extends AsyncTask< student, Void, Void>{

        private  studentDao mAsyncTaskDao;

        public updateAyncTask(studentDao mDao) {
            mAsyncTaskDao = mDao;
        }

        @Override
        protected Void doInBackground(student... students) {
            mAsyncTaskDao.update(students[0]);
            return null;
        }
    }
    // delete AsyncTask
    private static class deleteAyncTask extends AsyncTask< student, Void, Void>{

        private  studentDao mAsyncTaskDao;

        public deleteAyncTask(studentDao mDao) {
            mAsyncTaskDao = mDao;
        }

        @Override
        protected Void doInBackground(student... students) {
            mAsyncTaskDao.delete(students[0]);
            return null;
        }
    }

    //delete All
    private static class deleteAllAyncTask extends AsyncTask< Void, Void, Void>{

        private  studentDao mAsyncTaskDao;

        public deleteAllAyncTask(studentDao mDao) {
            mAsyncTaskDao = mDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAllStudents();
            return null;
        }
    }




}
