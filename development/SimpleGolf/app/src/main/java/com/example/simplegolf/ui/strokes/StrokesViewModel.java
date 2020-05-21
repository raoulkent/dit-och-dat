package com.example.simplegolf.ui.strokes;

import androidx.lifecycle.ViewModel;

import com.example.simplegolf.model.Scorecard;

public class StrokesViewModel extends ViewModel {

    private Scorecard scorecard;
    private int currentHole = 0;

    public Scorecard getScorecard() {
        return scorecard;
    }

    public void setScorecard(Scorecard scorecard) {
        this.scorecard = scorecard;
    }

    int getCurrentHole() {
        return currentHole;
    }

    public void setCurrentHole(int currentHole) {
        this.currentHole = currentHole;
    }
}