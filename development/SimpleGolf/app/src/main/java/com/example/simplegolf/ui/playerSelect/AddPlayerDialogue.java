package com.example.simplegolf.ui.playerSelect;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Tee;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddPlayerDialogue extends AppCompatDialogFragment {

    private TextInputLayout diaPlayerName;

    // made this one public because checkPlayerAbbr() method didn't find this field.
    private TextInputLayout diaPlayerAbbr;
    private TextInputLayout diaPlayerHCP;
    private Spinner spinner;
    private DialogListener listener;
    private Course course;
    private PlayerSelectViewModel viewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), R.style.AlertDialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogue_select_player, null);

        viewModel = new ViewModelProvider(getActivity()).get(PlayerSelectViewModel.class);

        if (getActivity().getIntent().hasExtra("course"))
            course = viewModel.getCourse();

        diaPlayerName = view.findViewById(R.id.diaPlayerName);
        diaPlayerAbbr = view.findViewById(R.id.diaPlayerAbbr);
        diaPlayerHCP = view.findViewById(R.id.diaPlayerHCP);
        spinner = view.findViewById(R.id.diaSpinTee);

        addSpinnerTees(course, spinner);

        builder.setView(view).setTitle("Vänligen ange spelardetaljer") // TODO: Use string resources!
                .setPositiveButton("Lägg till", null) //TODO: PositiveButton does not use correct colors
                .setNegativeButton("Avbryt", null); // TODO: NegativeButton does not use correct colors

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);

        alertDialog.setOnShowListener(dialogInterface -> {
            Button buttonPositive = ((AlertDialog) dialogInterface).getButton(alertDialog.BUTTON_POSITIVE);
            Button buttonNegative = ((AlertDialog) dialogInterface).getButton(alertDialog.BUTTON_NEGATIVE);

            buttonPositive.setOnClickListener(v -> {

                if (checkInput()) {
                    String name = diaPlayerName.getEditText().getText().toString();
                    String abbr = diaPlayerAbbr.getEditText().getText().toString();
                    String teeString = spinner.getSelectedItem().toString();
                    double hcp = Double.parseDouble(diaPlayerHCP.getEditText().getText().toString());

                    Tee tee = matchStringToTee(teeString);

                    listener.applyPlayerInfo(name, abbr, hcp, tee);
                    dialogInterface.dismiss();
                }
                // diaPlayerAbbr.setError("Initials must be entered");

            });

            buttonNegative.setOnClickListener(view1 -> dialogInterface.dismiss());

        });

        return alertDialog;
    }


    private void addSpinnerTees(Course c, Spinner s) {
        List<String> spinnerArray = new ArrayList<>();

        for (Tee tee : c.getTees())
            spinnerArray.add(tee.getName());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);
    }

    private Tee matchStringToTee(String teeName) {
        for (Tee t : course.getTees()) {
            if (t.getName().equals(teeName))
                return t;
        }
        return null;
    }

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
        void applyPlayerInfo(String name, String abbr, double hcp, Tee tee);
    }

    private boolean checkInput() {
        boolean isInputValid = true;

        diaPlayerName.setError(null);
        diaPlayerAbbr.setError(null);
        diaPlayerHCP.setError(null);

        if (!checkPlayerName())
            isInputValid = false;
        if (!checkPlayerAbbr())
            isInputValid = false;
        if (!checkPlayerHCP())
            isInputValid = false;

        return isInputValid;
    }

    private boolean checkPlayerName() {
        String playerName = diaPlayerName.getEditText().getText().toString();
        if (playerName.isEmpty()) {
            diaPlayerName.setError("Ange namn"); // TODO: Use string resources!
            return false;
        }
        return true;
    }

    private boolean checkPlayerAbbr() {
        String playerAbbr = diaPlayerAbbr.getEditText().getText().toString();
        if (playerAbbr.isEmpty()) {
            diaPlayerAbbr.setError("Ange initialer"); // TODO: Use string resources!
            return false;
        }
        return true;
    }

    private boolean checkPlayerHCP() {
        String playerHCP = diaPlayerHCP.getEditText().getText().toString();
        if (playerHCP.isEmpty()) {
            diaPlayerHCP.setError("Ange hcp"); // TODO: Use string resources!
            return false;
        }
        return true;
    }

}
