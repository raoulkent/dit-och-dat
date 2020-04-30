package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.database.AppDatabase;
import com.example.simplegolf.model.database.TestEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();



        db.entityDAO().insertAll(new TestEntity(4, "first", "last"));

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
