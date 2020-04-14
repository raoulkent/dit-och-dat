package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListeners();
    }

    private void setListeners() {

        Button newGame = (Button) findViewById(R.id.btn_newgame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Functionality to start a 'new game activity'
            }
        });

        // TODO: Add more buttons for multiplayer etc.
    }
}
