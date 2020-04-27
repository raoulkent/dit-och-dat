package com.example.simplegolf;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class GameActivityDialog extends AppCompatDialogFragment {

    private EditText edit_PlName;
    private EditText edit_PlAbbr;
    private EditText HCP;
    private Spinner Tee;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_game_dialog,null);

        builder.setView(view).setTitle("Please enter player details")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        edit_PlName = view.findViewById(R.id.editPlName);
        edit_PlAbbr = view.findViewById(R.id.editPlAbbr);
        HCP = view.findViewById(R.id.editPlHCP);
        Tee = view.findViewById(R.id.spinTee);

        return builder.create();
    }
}
