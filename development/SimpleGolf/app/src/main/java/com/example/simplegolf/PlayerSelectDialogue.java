package com.example.simplegolf;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PlayerSelectDialogue extends AppCompatDialogFragment {

    private EditText edit_PlName;
    private EditText edit_PlAbbr;
    private EditText HCP;
    private Spinner Tee;
    private DialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), R.style.AlertDialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_game_dialog, null);

        builder.setView(view).setTitle("Please enter player details")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                }).setPositiveButton("Add player", (dialogInterface, i) -> {
            String name = edit_PlName.getText().toString();
            String abbr = edit_PlAbbr.getText().toString();
            double hcp = Double.parseDouble(HCP.getText().toString());
            String tee = Tee.getSelectedItem().toString();
            listener.applyPlayerInfo(name, abbr, hcp, tee);
        });

        edit_PlName = view.findViewById(R.id.editPlName);
        edit_PlAbbr = view.findViewById(R.id.editPlAbbr);
        HCP = view.findViewById(R.id.editPlHCP);
        Tee = view.findViewById(R.id.spinTee);

        return builder.create();


    }// onCreateDialog

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }

    public interface DialogListener {
        void applyPlayerInfo(String name, String abbr, double HCP, String tee);
    }

}

