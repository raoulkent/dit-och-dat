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

import com.example.simplegolf.R;
import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Tee;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectDialogue extends AppCompatDialogFragment {

    private EditText edit_PlName;
    private EditText edit_PlAbbr;
    private EditText HCP;
    private Spinner Tee;
    private DialogListener listener;
    private Course course;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), R.style.AlertDialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogue_select_player, null);

        builder.setView(view).setTitle("Please enter player details")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                }).setPositiveButton("Add player", (dialogInterface, i) -> {
            String name = edit_PlName.getText().toString();
            String abbr = edit_PlAbbr.getText().toString();
            double hcp = Double.parseDouble(HCP.getText().toString());
            String tee = Tee.getSelectedItem().toString();
            listener.applyPlayerInfo(name, abbr, hcp, tee);
        });


//        if(getActivity().getIntent().hasExtra("courseTees"))
  //          course =(Course) getActivity().getIntent().getSerializableExtra("courseTees");

        edit_PlName = view.findViewById(R.id.editPlName);
        edit_PlAbbr = view.findViewById(R.id.editPlAbbr);
        HCP = view.findViewById(R.id.editPlHCP);
        Tee = view.findViewById(R.id.spinTee);

     //   addSpinnerTees(course, Tee);


        return builder.create();


    }// onCreateDialog


    /*
    public void addSpinnerTees(Course c, Spinner s){
        List<String> spinnerArray =  new ArrayList<String>();

        for (Tee tee:c.getTees())
            spinnerArray.add(tee.getName());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);
    }
*/

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

