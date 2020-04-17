package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;

public class GameActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    public static String N_HOLES = "nHoles";

    private Scorecard scorecard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        NumberPicker nrHolesPicker = findViewById(R.id.nrHolesPicker);
        nrHolesPicker.setMinValue(1);
        nrHolesPicker.setMaxValue(18);
        nrHolesPicker.setValue(9);
        nrHolesPicker.setOnValueChangedListener(this);
    }

    public void onClickCreate(View view) {

        NumberPicker nrHolesPicker = findViewById(R.id.nrHolesPicker);
        int nrHoles = nrHolesPicker.getValue();
        scorecard = new Scorecard(nrHoles);

        // TODO: Remove these test players
        scorecard.addPlayer("ABC");

        //Send holes to GameOverview for now, this will change to game object
        Intent startGame = new Intent(getApplicationContext(), GameOverview.class);
        startGame.putExtra("scorecard", scorecard);
        startActivity(startGame);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }
}
