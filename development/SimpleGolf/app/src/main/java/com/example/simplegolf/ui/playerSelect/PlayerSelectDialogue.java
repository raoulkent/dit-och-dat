package com.example.simplegolf.ui.playerSelect;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Tee;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectDialogue extends AppCompatDialogFragment {

    private TextInputLayout diaPlayerName;

    // made this one public because checkPlayerAbbr() method didn't find this field.
    public TextInputLayout diaPlayerAbbr;
    private TextInputLayout diaPlayerHCP;
    private Spinner tee;
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

        builder.setView(view).setTitle("Please enter player details")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                }).setPositiveButton("Add player", (dialogInterface, i) -> {
            String name = diaPlayerName.getEditText().getText().toString();
            String abbr = diaPlayerAbbr.getEditText().getText().toString();
            double hcp = Double.parseDouble(diaPlayerHCP.getEditText().getText().toString());
            String tee = this.tee.getSelectedItem().toString();


            if(checkPlayerAbbr()|checkPlayerName())
            listener.applyPlayerInfo(name, abbr, hcp, tee);
        });

        if (getActivity().getIntent().hasExtra("course"))
            course = viewModel.getCourse();

        diaPlayerName = view.findViewById(R.id.diaPlayerName);
        diaPlayerAbbr = view.findViewById(R.id.diaPlayerAbbr);
        diaPlayerHCP = view.findViewById(R.id.diaPlayerHCP);
        tee = view.findViewById(R.id.diaSpinTee);

        addSpinnerTees(course, tee);

        return builder.create();
    }


    // TODO: Implement that the spinner is populated with the values from the ViewModel
    private void addSpinnerTees(Course c, Spinner s) {
        List<String> spinnerArray = new ArrayList<>();

        for (Tee tee : c.getTees())
            spinnerArray.add(tee.getName());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);
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
        void applyPlayerInfo(String name, String abbr, double hcp, String tee);
    }

    // Below are the methods for checking correct input

    public boolean checkPlayerName(){
        String playerName = diaPlayerName.getEditText().getText().toString();

        if(playerName.isEmpty()) {
            diaPlayerName.setError("A player name must be entered");
            return false;
        }
        else if(playerName.length()>25){
            diaPlayerName.setError("Player name must be under 25 characters");
        }
        else{
            diaPlayerName.setError(null);
        }
        return true;
    }

    public boolean checkPlayerAbbr(){

        String playerAbbr = diaPlayerAbbr.getEditText().getText().toString();
        if(playerAbbr.isEmpty()) {
            diaPlayerName.setError("A player name must be entered");
            return false;
        }
        else if(playerAbbr.length()>3){
            diaPlayerName.setError("Maximum 3 characters");
        }
        else{
            diaPlayerName.setError(null);
        }
        return true;
    }

    public boolean checkPlayerHCP(){

        double playerHCP = Double.parseDouble(diaPlayerHCP.getEditText().getText().toString());


        return false;
    }

}

