package com.example.simplegolf;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ConfirmDialog extends AppCompatDialogFragment {
    private String question;
    //    MaterialButton confirm, cancel;
    private ConfirmDialogListener listener;

    public ConfirmDialog(ConfirmDialogListener listener, String question) {
        this.listener = listener;
        this.question = question;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireActivity());

        builder.setTitle(question)
                .setPositiveButton(R.string.confirm, null)
                .setNegativeButton(R.string.abort, null);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);

        alertDialog.setOnShowListener(dialog -> {
            Button buttonPositive = ((AlertDialog) dialog).getButton(alertDialog.BUTTON_POSITIVE);
            Button buttonNegative = ((AlertDialog) dialog).getButton(alertDialog.BUTTON_NEGATIVE);

            buttonPositive.setOnClickListener(v -> {
                dialog.dismiss();
                listener.confirm();
            });
            buttonNegative.setOnClickListener(v -> dialog.dismiss());
        });

        return alertDialog;
    }

    public interface ConfirmDialogListener {
        void confirm();
    }
}