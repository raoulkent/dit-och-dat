package com.example.simplegolf.ui.playerselect;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Tee;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditPlayerDialog extends AddPlayerDialog {
    private PlayerSelectViewModel viewModel;
    private Player player;

    EditPlayerDialog(Player player) {
        this.player = player;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireActivity(), R.style.AlertDialogTheme);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogue_select_player, null);

        this.viewModel = new ViewModelProvider(requireActivity()).get(PlayerSelectViewModel.class);

        if (requireActivity().getIntent().hasExtra("course"))
            course = viewModel.getCourse();

        diaPlayerAbbr = view.findViewById(R.id.diaPlayerAbbr);
        diaPlayerHCP = view.findViewById(R.id.diaPlayerHCP);
        diaDropdownTee = view.findViewById(R.id.diaDropdownTee);
        diaDropdownTee.setBackgroundColor(Color.TRANSPARENT);

        // Adding tees to dropdown menu /Joar
        List<String> teeStrings = new ArrayList<>();
        for(Tee tee:course.getTees())
            teeStrings.add(tee.getName());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        requireContext(),
                        R.layout.dia_dropdown_menu_item,
                        teeStrings);
        diaDropdownTee.setAdapter(adapter);

        Objects.requireNonNull(diaPlayerAbbr.getEditText()).setText(player.getInitials());
        Objects.requireNonNull(diaPlayerHCP.getEditText()).setText(String.valueOf(player.getHcp()));

        diaDropdownTee.setText(player.getTee().getName(),false);

        builder.setView(view).setTitle(R.string.request_player_details)
                .setPositiveButton(R.string.add, null)
                .setNegativeButton(R.string.abort, null);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);

        alertDialog.setOnShowListener(dialogInterface -> {
            Button buttonPositive = ((AlertDialog) dialogInterface).getButton(alertDialog.BUTTON_POSITIVE);
            Button buttonNegative = ((AlertDialog) dialogInterface).getButton(alertDialog.BUTTON_NEGATIVE);

            buttonPositive.setOnClickListener(v -> {
                if (checkInput()) {
                    String abbr = diaPlayerAbbr.getEditText().getText().toString();
                    String teeString = diaDropdownTee.getText().toString();
                    double hcp = Double.parseDouble(diaPlayerHCP.getEditText().getText().toString());

                    Tee tee = matchStringToTee(teeString);

                    listener.editPlayerInfo(player, abbr, abbr.toUpperCase(), hcp, tee);
                    dialogInterface.dismiss();
                }
            });

            buttonNegative.setOnClickListener(view1 -> dialogInterface.dismiss());

        });

        return alertDialog;
    }
}