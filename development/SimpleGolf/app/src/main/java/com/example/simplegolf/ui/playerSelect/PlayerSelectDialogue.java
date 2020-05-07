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

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectDialogue extends AppCompatDialogFragment {

    private EditText edit_PlName;
    private EditText edit_PlAbbr;
    private EditText hdcp;
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
            String name = edit_PlName.getText().toString();
            String abbr = edit_PlAbbr.getText().toString();
            double hcp = Double.parseDouble(hdcp.getText().toString());
            String tee = this.tee.getSelectedItem().toString();
            listener.applyPlayerInfo(name, abbr, hcp, tee);
        });

        if (getActivity().getIntent().hasExtra("course"))
            course = viewModel.getCourse();

        edit_PlName = view.findViewById(R.id.editPlName);
        edit_PlAbbr = view.findViewById(R.id.editPlAbbr);
        hdcp = view.findViewById(R.id.editPlHCP);
        tee = view.findViewById(R.id.spinTee);

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

}

