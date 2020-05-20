package com.example.simplegolf.ui.gamehistory;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.simplegolf.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ConfirmDialog extends AppCompatDialogFragment {
    String question;
//    MaterialButton confirm, cancel;
    ConfirmDialogListener listener;

    public ConfirmDialog(String question) {
        this.question = question;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireActivity(), R.style.AlertDialogTheme);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        // TODO: Create a layout for the confirmation dialog
//        View view inflater.inflate(R.layout.dialog_confirmation, null);
        View view = new View(requireActivity());

        builder.setView(view).setTitle(question)
                .setPositiveButton(R.string.confirm, null)
                .setNegativeButton(R.string.abort, null);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);

        alertDialog.setOnShowListener(dialog -> {
            Button buttonPositive = ((AlertDialog) dialog).getButton(alertDialog.BUTTON_POSITIVE);
            Button buttonNegative = ((AlertDialog) dialog).getButton(alertDialog.BUTTON_NEGATIVE);

            buttonPositive.setOnClickListener(v -> {
                listener.confirm();
                dialog.dismiss();
            });
            buttonNegative.setOnClickListener(v -> dialog.dismiss());
        });

        return alertDialog;
    }

    public interface ConfirmDialogListener {
        void confirm();
    }
}