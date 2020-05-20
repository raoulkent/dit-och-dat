package com.example.simplegolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.testcourses.TestCourses;

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

    public void startGame(View view) {
        int nPlayers = 1;
        switch (view.getId()) {
            case R.id.start1p:
                nPlayers = 1;
                break;
            case R.id.start2p:
                nPlayers = 2;
                break;
            case R.id.start3p:
                nPlayers = 3;
                break;
            case R.id.start4p:
                nPlayers = 4;
                break;
        }
        System.out.println(nPlayers);
        Scorecard scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());

        for (int p = 1; p <= nPlayers; p++) {
            scorecard.addPlayer("Player " + p, "P" + p, TestCourses.INSTANCE.getCourseChalmers().getTees().get(0), 0);
        }

        Intent startGame = new Intent(getApplicationContext(), GameActivity.class);
        startGame.putExtra("scorecard", scorecard);
        startActivity(startGame);
    }
}
