package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
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
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = Repository.getRepository(this);

        repository.getDb().courseDAO().insert(TestCourses.INSTANCE.getCourseChalmers())
                .subscribeOn(Schedulers.io())
                .subscribe();

        repository.getDb().courseDAO().getAll().observe(this, data -> {
            course = data.get(0);

            repository.getDb().playerDAO().get(1).observe(this, data2 -> {
                data2.setCourse(course);
                data2.setTee(course.getTees().get(0));
            });
        });

        Player p = new Player("PHT", TestCourses.INSTANCE.getCourseChalmers(), TestCourses.INSTANCE.getCourseChalmers().getTees().get(0), 36.0);

        repository.getDb().playerDAO().insert(p)
                .subscribeOn(Schedulers.io())
                .subscribe();

        Scorecard scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());

        repository.getDb().scorecardDAO().insert(scorecard).subscribeOn(Schedulers.io()).subscribe();



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
