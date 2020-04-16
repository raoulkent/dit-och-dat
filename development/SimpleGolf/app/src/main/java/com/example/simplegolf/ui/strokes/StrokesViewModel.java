package com.example.simplegolf.ui.strokes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StrokesViewModel extends ViewModel {

    private int nHoles = 0;
    private int currenthole = 0;
    private int[] shots = new int[18];

    public int getnHoles() {
        return nHoles;
    }

    public void setnHoles(int nHoles) {
        this.nHoles = nHoles;
    }

    public int getCurrenthole() {
        return currenthole;
    }

    public void setCurrenthole(int currenthole) {
        this.currenthole = currenthole;
    }

    public int getShots(int holeNumber) {
        return shots[holeNumber];
    }

    public void incrementShots(int holeNumber) {
        shots[holeNumber]++;
    }

    public void decrementShots(int holeNumber) {
        shots[holeNumber]--;
    }
}