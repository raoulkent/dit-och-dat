package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.simplegolf.model.Scorecard;

public class GameActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

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

        //TODO: Add functionality to button
        // method to generate the scorecard
    }

    public void  onClickCreate(View view){

        NumberPicker nrHolesPicker = findViewById(R.id.nrHolesPicker);
        int nrHoles = nrHolesPicker.getValue();
        //TextView nrOfHoles = findViewById(R.id.nrOfHoleSelect);
        //int nrHoles = Integer.parseInt(nrOfHoles.getText().toString());
        scorecard = new Scorecard(nrHoles);

        //Send holes to GameOverview for now, this will change to game object
        Intent startGame = new Intent(getApplicationContext(), GameOverview.class);
        startGame.putExtra("nHoles", nrHoles);
        startActivity(startGame);
    }


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }
}
