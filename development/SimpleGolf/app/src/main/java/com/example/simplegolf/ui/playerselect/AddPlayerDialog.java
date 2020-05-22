package com.example.simplegolf.ui.playerselect;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Tee;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddPlayerDialog extends AppCompatDialogFragment {
    // made this one public because checkPlayerAbbr() method didn't find this field.
    TextInputLayout diaPlayerName, diaPlayerAbbr, diaPlayerHCP, diaParentDropdownTee;
    AutoCompleteTextView diaDropdownTee;
    Spinner spinner;
    PlayerDialogListener listener;
    Course course;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogue_select_player, container);

        PlayerSelectViewModel viewModel = new ViewModelProvider(requireActivity()).get(PlayerSelectViewModel.class);

        if (getActivity().getIntent().hasExtra("course")) {
            course = viewModel.getCourse();
        }

        diaPlayerAbbr = view.findViewById(R.id.diaPlayerAbbr);
        diaPlayerHCP = view.findViewById(R.id.diaPlayerHCP);
        diaParentDropdownTee = view.findViewById(R.id.diaParentdropdownTee);
        diaDropdownTee = view.findViewById(R.id.diaDropdownTee);

        diaDropdownTee.setBackgroundColor(Color.TRANSPARENT);

        addDropdownTees(course, diaDropdownTee);

        Button btn = view.findViewById(R.id.btnAddPlayer);

        btn.setOnClickListener((v) -> {
            if (checkInput()) {
                // String name = diaPlayerName.getEditText().getText().toString();
                String abbr = diaPlayerAbbr.getEditText().getText().toString();

                String teeString = diaDropdownTee.getText().toString();
                //String teeString = spinner.getSelectedItem().toString();
                double hcp = Double.parseDouble(diaPlayerHCP.getEditText().getText().toString());

                Tee tee = matchStringToTee(teeString);

                listener.newPlayerInfo(abbr, abbr.toUpperCase(), hcp, tee);
                dismiss();
            }
        });



        return view;
    }



    void addDropdownTees(Course c, AutoCompleteTextView autoCompleteTextView){

        List<String> teeList = new ArrayList<>();

        for (Tee tee : c.getTees()) {
            teeList.add(tee.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.dia_dropdown_menu_item, teeList);

        autoCompleteTextView.setAdapter(adapter);
    }

    void addSpinnerTees(Course c, Spinner s) {
        List<String> spinnerArray = new ArrayList<>();

        for (Tee tee : c.getTees())
            spinnerArray.add(tee.getName());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);
    }

    Tee matchStringToTee(String teeName) {
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
            listener = (PlayerDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }

    public interface PlayerDialogListener {
        void newPlayerInfo(String name, String abbr, double hcp, Tee tee);
        void editPlayerInfo(Player player, String name, String abbr, double hcp, Tee tee);
    }

    boolean checkInput() {
        boolean isInputValid = true;

      //  diaPlayerName.setError(null);
        diaPlayerAbbr.setError(null);
        diaPlayerHCP.setError(null);
        diaDropdownTee.setError(null);

   //     if (!checkPlayerName())
     //       isInputValid = false;
        if (!checkPlayerAbbr())
            isInputValid = false;
        if (!checkPlayerHcp())
            isInputValid = false;
        if(!checkPlayerTee())
            isInputValid = false;

        return isInputValid;
    }

    boolean checkPlayerName() {
        String playerName = diaPlayerName.getEditText().getText().toString();
        if (playerName.isEmpty()) {
            diaPlayerName.setError(getString(R.string.specify_name));
            return false;
        }
        return true;
    }

    boolean checkPlayerAbbr() {
        String playerAbbr = diaPlayerAbbr.getEditText().getText().toString();
        if (playerAbbr.isEmpty()) {
            diaPlayerAbbr.setError(getString(R.string.specify_abbreviated_name));
            return false;
        }
        return true;
    }

    boolean checkPlayerHcp() {
        String playerHCP = diaPlayerHCP.getEditText().getText().toString();
        if (playerHCP.isEmpty()) {
            diaPlayerHCP.setError(getString(R.string.specify_handicap));
            return false;
        } else if (Double.parseDouble(playerHCP) > 54.0 || Double.parseDouble(playerHCP) < 0) {
            diaPlayerHCP.setError("Ange hcp mellan 0 och 54");
            return false;
        }

        return true;
    }

    boolean checkPlayerTee(){
       if(diaDropdownTee.getText().toString().isEmpty()){
            diaParentDropdownTee.setError("Välj tee");
           return false;
       }



        return true;
    }

}

