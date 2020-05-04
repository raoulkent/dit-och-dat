package com.example.simplegolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newGame(View view) {
        Intent startGame = new Intent(getApplicationContext(), CourseSelectActivity.class);
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
