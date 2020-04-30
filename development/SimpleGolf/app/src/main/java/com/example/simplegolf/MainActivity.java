package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.database.AppDatabase;
import com.example.simplegolf.model.database.TestEntity;
import com.example.simplegolf.model.testcourses.TestCourses;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = Repository.getRepository(this);

        repository.getDb().courseDAO().insert(TestCourses.INSTANCE.getCourseChalmers())
                .subscribeOn(Schedulers.io())
                .subscribe();

    }

    public void newGame(View view) {
        Intent startGame = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(startGame);
    }

    public void unFinishedGame(View v) {
        Intent startOldGame = new Intent(getApplicationContext(), ViewOldGamesActivity.class);
        startOldGame.putExtra("finished", false);
        startActivity(startOldGame);
    }

    public void finishedGame(View v) {
        Intent startOldGame = new Intent(getApplicationContext(), ViewOldGamesActivity.class);
        startOldGame.putExtra("finished", true);
        startActivity(startOldGame);
    }
}
