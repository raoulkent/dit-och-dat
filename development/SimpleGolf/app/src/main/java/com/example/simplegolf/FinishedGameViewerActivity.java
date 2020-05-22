package com.example.simplegolf;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplegolf.ui.scorecard.ScorecardFragment;

public class FinishedGameViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finished_game_viewer_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new ScorecardFragment())
                    .commitNow();
        }
    }
}