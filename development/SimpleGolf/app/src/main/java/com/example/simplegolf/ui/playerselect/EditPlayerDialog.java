package com.example.simplegolf.ui.playerselect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Tee;

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
        Objects.requireNonNull(diaPlayerAbbr.getEditText()).setText(player.getInitials());
        Objects.requireNonNull(diaPlayerHCP.getEditText()).setText(String.valueOf(player.getHcp()));
        diaDropdownTee.setText(player.getTee().getName(), false);

        View item = toolbar.findViewById(R.id.action_name);
        item.setOnClickListener(v -> editPlayer());

        return view;
    }

    private void editPlayer() {
        if (checkInput()) {
            String abbr = Objects.requireNonNull(diaPlayerAbbr.getEditText()).getText().toString();
            String teeString = diaDropdownTee.getText().toString();
            double hcp = Double.parseDouble(Objects.requireNonNull(diaPlayerHCP.getEditText()).getText().toString());

            Tee tee = matchStringToTee(teeString);

            listener.editPlayerInfo(player, abbr, abbr.toUpperCase(), hcp, tee);
            dismiss();
        }
    }
}