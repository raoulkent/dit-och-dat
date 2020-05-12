package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.simplegolf.model.Repository;

public class DeveloperActivity extends AppCompatActivity {

    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        repository = Repository.getRepository(this);
    }

    public void delCourses(View view) {
        new Thread(() -> repository.getDb().courseDAO().deleteAll()).start();
    }

    public void delUnfinished(View view) {
        new Thread(() -> repository.getDb().scorecardDAO().deleteAllUnfinished()).start();
    }

    public void delFinished(View view) {
        new Thread(() -> repository.getDb().scorecardDAO().deleteAllFinished()).start();
    }

    public void delAllRounds(View view) {
        new Thread(() -> repository.getDb().scorecardDAO().deleteAll()).start();
    }
}
