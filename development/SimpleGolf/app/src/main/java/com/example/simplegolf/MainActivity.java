package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.testcourses.TestCourses;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = Repository.getRepository(this);

        new Thread(() -> {
            repository.getDb().scorecardDAO().insert(new Scorecard(TestCourses.INSTANCE.getCourseChalmers()));
        }).start();

        repository.getDb().scorecardDAO().getUnfinishedRounds().observe(this, data -> {
            Log.d("DebugDB", "Course: " + data.get(0).getPlayers().get(0).getShotsForHole(0));

        });




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
