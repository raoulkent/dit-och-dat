package com.example.simplegolf.ui.strokes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StrokesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StrokesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}