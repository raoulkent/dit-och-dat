package com.example.simplegolf.ui.strokes;

import androidx.lifecycle.ViewModel;

public class StrokesViewModel extends ViewModel {

    private int nHoles = 0;
    private int currentHole = 0;

    int getNHoles() {
        return nHoles;
    }

    public void setNHoles(int nHoles) {
        this.nHoles = nHoles;
    }

    int getCurrentHole() {
        return currentHole;
    }

    void setCurrentHole(int currentHole) {
        this.currentHole = currentHole;
    }
}