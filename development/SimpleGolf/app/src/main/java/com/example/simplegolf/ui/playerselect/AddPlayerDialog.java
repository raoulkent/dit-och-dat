package com.example.simplegolf.ui.playerselect;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Tee;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddPlayerDialog extends AppCompatDialogFragment {
    TextInputLayout diaPlayerAbbr, diaPlayerHCP, diaParentDropdownTee;
    AutoCompleteTextView diaDropdownTee;
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
        View view = inflater.inflate(R.layout.dialogue_select_player, container, false);

        MaterialToolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        View item = toolbar.findViewById(R.id.action_name);
        item.setOnClickListener(v -> addPlayer());

        PlayerSelectViewModel viewModel = new ViewModelProvider(requireActivity()).get(PlayerSelectViewModel.class);

        if (requireActivity().getIntent().hasExtra("course")) {
            course = viewModel.getCourse();
        }

        diaPlayerAbbr = view.findViewById(R.id.diaPlayerAbbr);
        diaPlayerHCP = view.findViewById(R.id.diaPlayerHCP);
        diaParentDropdownTee = view.findViewById(R.id.diaParentdropdownTee);
        diaDropdownTee = view.findViewById(R.id.diaDropdownTee);

        diaDropdownTee.setBackgroundColor(Color.TRANSPARENT);

        addDropdownTees(course, diaDropdownTee);

        diaDropdownTee.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                diaDropdownTee.showDropDown();
            }
        });

        return view;
    }

    private void addPlayer() {
        if (checkInput()) {
            String abbr = Objects.requireNonNull(diaPlayerAbbr.getEditText()).getText().toString();
            String teeString = diaDropdownTee.getText().toString();
            double hcp = Double.parseDouble(Objects.requireNonNull(diaPlayerHCP.getEditText()).getText().toString());
            Tee tee = matchStringToTee(teeString);

            listener.newPlayerInfo(abbr, abbr.toUpperCase(), hcp, tee);
            dismiss();
        }
    }

    private void addDropdownTees(Course c, AutoCompleteTextView autoCompleteTextView) {
        List<String> teeList = new ArrayList<>();

        for (Tee tee : c.getTees()) {
            teeList.add(tee.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.dia_dropdown_menu_item, teeList);
        autoCompleteTextView.setAdapter(adapter);
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
        if (!checkPlayerTee())
            isInputValid = false;

        return isInputValid;
    }

    private boolean checkPlayerAbbr() {
        String playerAbbr = Objects.requireNonNull(diaPlayerAbbr.getEditText()).getText().toString();
        if (playerAbbr.isEmpty()) {
            diaPlayerAbbr.setError(getString(R.string.specify_abbreviated_name));
            return false;
        }
        return true;
    }

    private boolean checkPlayerHcp() {
        String playerHCP = Objects.requireNonNull(diaPlayerHCP.getEditText()).getText().toString();
        if (playerHCP.isEmpty()) {
            diaPlayerHCP.setError(getString(R.string.specify_handicap));
            return false;
        } else if (Double.parseDouble(playerHCP) > 54.0 || Double.parseDouble(playerHCP) < 0) {
            diaPlayerHCP.setError(getString(R.string.error_hcp_range));
            return false;
        }

        return true;
    }

    private boolean checkPlayerTee() {
        if (diaDropdownTee.getText().toString().isEmpty()) {
            diaParentDropdownTee.setError(getString(R.string.select_tee));
            return false;
        }

        String teeString = diaDropdownTee.getText().toString();
        Tee tee = matchStringToTee(teeString);

        if (tee == null) {
            diaParentDropdownTee.setError(getString(R.string.error_select_valid_tee));
            return false;
        }


        return true;
    }
}

