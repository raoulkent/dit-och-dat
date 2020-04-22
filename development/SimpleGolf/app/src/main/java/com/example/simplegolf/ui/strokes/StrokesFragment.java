package com.example.simplegolf.ui.strokes;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Scorecard;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Objects;

public class StrokesFragment extends Fragment implements View.OnClickListener {

    private StrokesViewModel viewModel;
    private static final String ARG_HOLE = "holeNumber";
    private int holeNumber;

    private Scorecard scorecard;

    private LinearLayout layout;
    private ArrayList<TextView> counters;

    /**
     * Factory method to create new instances of this fragment
     *
     * @param holeNumber
     * @return A new instance of StrokesFragment
     */
    static StrokesFragment newInstance(int holeNumber) {
        StrokesFragment fragment = new StrokesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_HOLE, holeNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public StrokesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            holeNumber = getArguments().getInt(ARG_HOLE);
        }
        viewModel = new ViewModelProvider(getActivity()).get(StrokesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scorecard = (Scorecard) requireActivity().getIntent().getSerializableExtra("scorecard");

        View root = inflater.inflate(R.layout.fragment_strokes, container, false);
        layout = root.findViewById(R.id.playerLayout);
        counters = new ArrayList<>();
        addPlayers(root);
        updateUI();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onClick(View v) {

    }

    private void addPlayers(View root){

        for(int p=0; p<scorecard.getPlayers().size(); p++){
            LinearLayout col = new LinearLayout(getActivity());
            col.setOrientation(LinearLayout.VERTICAL);
            col.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            TextView name = new TextView(getActivity());
            name.setText(scorecard.getPlayers().get(p).getInitials());
            name.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            name.setTextSize(30);
            col.addView(name);

            Button addP1 = new MaterialButton(getActivity());
            addP1.setText("add");
            int finalP = p;
            addP1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scorecard.getPlayers().get(finalP).incrementHole(holeNumber);
                    updateUI();
                }
            });
            col.addView(addP1);

            TextView stat = new TextView(getActivity());
            stat.setText("0");
            stat.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            stat.setTextSize(60);
            counters.add(stat);
            col.addView(stat);


            Button removeP1 = new MaterialButton(getActivity());
            removeP1.setText("remove");
            removeP1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scorecard.getPlayers().get(finalP).decrementHole(holeNumber);
                    updateUI();
                }
            });
            col.addView(removeP1);

            layout.addView(col);
        }
    }

    private void updateUI() {
        for(int p=0; p<scorecard.getPlayers().size(); p++){
            counters.get(p).setText(String.valueOf(scorecard.getPlayers().get(p).getShotsForHole(holeNumber)));
        }
    }
}
