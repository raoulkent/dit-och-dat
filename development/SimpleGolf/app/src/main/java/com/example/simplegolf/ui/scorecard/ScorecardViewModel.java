package com.example.simplegolf.ui.scorecard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScorecardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ScorecardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}