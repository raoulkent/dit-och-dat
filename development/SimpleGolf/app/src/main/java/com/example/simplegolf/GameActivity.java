package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;

public class GameActivity extends AppCompatActivity {

    private Scorecard scorecard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //TODO: Add functionality to button
        // method to generate the scorecard
    }

    public void onClickCreate(View view) {
        TextView nrOfHoles = findViewById(R.id.nrOfHoleSelect);
        int nrHoles = Integer.parseInt(nrOfHoles.getText().toString());
        scorecard = new Scorecard(nrHoles);

        // TODO: Remove these test players
        scorecard.addPLayerToList(new Player("LOL", nrHoles));
        scorecard.addPLayerToList(new Player("WOW", nrHoles));

        //Send holes to GameOverview for now, this will change to game object
        Intent startGame = new Intent(getApplicationContext(), GameOverview.class);
        startGame.putExtra("scorecard", scorecard);
        startActivity(startGame);
    }
}