package com.example.simplegolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.database.AppDatabase;

public class StartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        TextView textFinished = findViewById(R.id.textFinished);
        TextView textUnFinished = findViewById(R.id.textUnfinished);

        View newGameCard = findViewById(R.id.newGameCard);
        newGameCard.setOnClickListener(v -> newGame());

        View unfinishedCard = findViewById(R.id.unfinishedCard);
        unfinishedCard.setOnClickListener(v -> unFinishedGame());

        View finishedCard = findViewById(R.id.finishedCard);
        finishedCard.setOnClickListener(v -> finishedGame());

        new Thread(() -> {
            AppDatabase db = Repository.getRepository(this).getDb();
            int nUnfinished = db.scorecardDAO().getUnfinishedRounds().size();
            int nFinished = db.scorecardDAO().getFinishedRounds().size();

            runOnUiThread(() -> {
                textUnFinished.setText("Oavslutade rundor (" + nUnfinished + ")");
                textFinished.setText("Avslutade rundor (" + nFinished + ")");
            });
        }).start();
    }

    public void newGame() {
        Intent startGame = new Intent(getApplicationContext(), CourseSelectActivity.class);
        startActivity(startGame);
    }

    public void unFinishedGame() {
        Intent startOldGame = new Intent(getApplicationContext(), GameHistoryActivity.class);
        startOldGame.putExtra("finished", false);
        startActivity(startOldGame);
    }

    public void finishedGame() {
        Intent startOldGame = new Intent(getApplicationContext(), GameHistoryActivity.class);
        startOldGame.putExtra("finished", true);
        startActivity(startOldGame);
    }
}
