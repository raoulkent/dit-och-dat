package com.example.simplegolf.ui.playerSelect;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PlayerEditDialogue extends AddPlayerDialogue {
    private PlayerSelectViewModel viewModel;

    public PlayerEditDialogue(PlayerSelectViewModel viewModel){
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}
