package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

    public void  onClickCreate(View view){
        TextView nrOfHoles = findViewById(R.id.nrOfHoleSelect);
        int nrHoles = Integer.parseInt(nrOfHoles.getText().toString());
        scorecard = new Scorecard(nrHoles);

    }


}
