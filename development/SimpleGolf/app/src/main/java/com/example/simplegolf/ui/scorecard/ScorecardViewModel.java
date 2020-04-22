package com.example.simplegolf.ui.scorecard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScorecardViewModel extends ViewModel {
    private Boolean showStrokes;

    public ScorecardViewModel() {
        this.showStrokes = true;
    }

    public void setShowStrokes(Boolean showStrokes){
        this.showStrokes = showStrokes;
    }

    public Boolean getShowStrokes(){
        return showStrokes;
    }

}