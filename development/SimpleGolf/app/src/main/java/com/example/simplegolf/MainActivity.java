package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.simplegolf.model.database.AppDatabase;
import com.example.simplegolf.model.database.TestEntity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").fallbackToDestructiveMigration().build();


        db.entityDAO().insertAll(new TestEntity("aa", "bb"))
                .subscribeOn(Schedulers.io())
                .subscribe();


        db.entityDAO().getAll().observe(this, data -> {
            Log.d("LOGDb", "Size:  " + data.size());

            for (TestEntity t : data) {
                Log.d("LOGDb", "id " + t.uid + " name: " + t.firstName);
            }
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
