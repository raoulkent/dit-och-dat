package com.example.simplegolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.database.AppDatabase;
import com.google.android.material.card.MaterialCardView;

public class StartScreenActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        View newGameCard = findViewById(R.id.newGameCard);
        newGameCard.setOnClickListener(v -> newGame());

        View unfinishedCard = findViewById(R.id.unfinishedCard);
        unfinishedCard.setOnClickListener(v -> unFinishedGame());

        View finishedCard = findViewById(R.id.finishedCard);
        finishedCard.setOnClickListener(v -> finishedGame());

        // Onclick listener for developer options.
        View developerCard = findViewById(R.id.developerCard);
        developerCard.setOnClickListener(v -> developerOptions());

        loadNumberOfRounds();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNumberOfRounds();
    }

    public void loadNumberOfRounds() {
        TextView textFinished = findViewById(R.id.textFinished);
        TextView textUnFinished = findViewById(R.id.textUnfinished);
        new Thread(() -> {
            AppDatabase db = Repository.getRepository(this).getDb();
            int nUnfinished = db.scorecardDAO().getUnfinishedRounds().size();
            int nFinished = db.scorecardDAO().getFinishedRounds().size();

            runOnUiThread(() -> {
                textUnFinished.setText(getString(R.string.unfinished_rounds) + " (" + nUnfinished + ")");
                textFinished.setText(getString(R.string.finished_rounds) + " (" + nFinished + ")");

                if (nUnfinished == 0) {
                    setUnfinishedBtnState(false);
                } else {
                    setUnfinishedBtnState(true);
                }

                if (nFinished == 0) {
                    setFinishedBtnState(false);
                } else {
                    setFinishedBtnState(true);
                }
            });
        }).start();
    }

    public void setUnfinishedBtnState(boolean enabled) {
        MaterialCardView cardUnfinished = findViewById(R.id.unfinishedCard);
        TextView textUnfinished = findViewById(R.id.textUnfinished);
        ImageView imageUnfinished = findViewById(R.id.iconUnfinished);
        if (enabled) {
            textUnfinished.setTextColor(getColor(R.color.black));
            imageUnfinished.getDrawable().setTint(getColor(R.color.black));
            cardUnfinished.setOnClickListener(v -> unFinishedGame());
        }
        else {
            textUnfinished.setTextColor(getColor(R.color.gray));
            imageUnfinished.getDrawable().setTint(getColor(R.color.gray));
            cardUnfinished.setOnClickListener(v -> {
                Toast.makeText(this, R.string.no_unfinished_rounds, Toast.LENGTH_SHORT).show();
            });
        }
    }

    public void setFinishedBtnState(boolean enabled) {
        MaterialCardView cardFinished = findViewById(R.id.finishedCard);
        TextView textFinished = findViewById(R.id.textFinished);
        ImageView imageFinished = findViewById(R.id.iconFinished);
        if (enabled) {
            textFinished.setTextColor(getColor(R.color.black));
            imageFinished.getDrawable().setTint(getColor(R.color.black));
            cardFinished.setOnClickListener(v -> finishedGame());
        }
        else {
            textFinished.setTextColor(getColor(R.color.gray));
            imageFinished.getDrawable().setTint(getColor(R.color.gray));
            cardFinished.setOnClickListener(v -> {
                Toast.makeText(this, R.string.no_finished_rounds, Toast.LENGTH_SHORT).show();
            });
        }
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

    public void developerOptions() {
        Intent developerActivity = new Intent(getApplicationContext(), DeveloperActivity.class);
        startActivity(developerActivity);
    }
}
