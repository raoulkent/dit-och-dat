package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.simplegolf.model.Scorecard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newGame(View view) {
        Intent startGame = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(startGame);
    }
}