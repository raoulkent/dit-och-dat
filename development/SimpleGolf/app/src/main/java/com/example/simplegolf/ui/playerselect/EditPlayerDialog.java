package com.example.simplegolf.ui.playerselect;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Tee;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditPlayerDialog extends AddPlayerDialog {
    private Player player;

    EditPlayerDialog(Player player) {
        this.player = player;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        diaPlayerAbbr.getEditText().setText(player.getInitials());
        diaPlayerHCP.getEditText().setText(String.valueOf(player.getHcp()));
        diaDropdownTee.setText(player.getTee().getName());

        View item = toolbar.findViewById(R.id.action_name);
        item.setOnClickListener(v -> editPlayer());


        return view;
    }

    private void editPlayer() {
        if (checkInput()) {
            // String name = diaPlayerName.getEditText().getText().toString();
            String abbr = diaPlayerAbbr.getEditText().getText().toString();
            //String teeString = spinner.getSelectedItem().toString();
            String teeString = diaDropdownTee.getText().toString();
            double hcp = Double.parseDouble(diaPlayerHCP.getEditText().getText().toString());

            Tee tee = matchStringToTee(teeString);

            listener.editPlayerInfo(player, abbr, abbr.toUpperCase(), hcp, tee);
            dismiss();
        }
    }
}