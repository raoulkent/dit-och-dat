package com.example.simplegolf.ui.strokes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Scorecard;

import java.util.Objects;

public class StrokesFragment extends Fragment implements View.OnClickListener {

    private StrokesViewModel viewModel;
    private static final String ARG_HOLE = "holeNumber";
    private int holeNumber;

    private TextView counter;

    private Scorecard scorecard;

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
        Log.d("SWIPE", "N HOLES" + viewModel.getNHoles());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scorecard = (Scorecard) requireActivity().getIntent().getSerializableExtra("scorecard");
        return inflater.inflate(R.layout.fragment_strokes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button remove = view.findViewById(R.id.remove);
        Button add = view.findViewById(R.id.add);                       // Stroke counter for p1
        counter = view.findViewById(R.id.counter);
        add.setOnClickListener(this);
        remove.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add: {
                scorecard.getPlayers().get(0).incrementHole(holeNumber);
                updateUI();
                break;
            }
            case R.id.remove: {
                scorecard.getPlayers().get(0).decrementHole(holeNumber);
                updateUI();
                break;
            }
        }
    }

    private void updateUI() {
        counter.setText(String.valueOf(scorecard.getPlayers().get(0).getShotsForHole(holeNumber)));
    }
}
