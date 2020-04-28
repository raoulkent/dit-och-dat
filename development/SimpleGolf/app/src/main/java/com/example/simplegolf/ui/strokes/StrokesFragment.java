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
import com.example.simplegolf.model.Player;
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
        View root = inflater.inflate(R.layout.fragment_strokes, container, false);

        scorecard = (Scorecard) requireActivity().getIntent().getSerializableExtra("scorecard");
        counters = new ArrayList<>();
        layout = root.findViewById(R.id.playerLayout);

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
        for(Player p: scorecard.getPlayers()){
            layout.addView(createLayoutForPlayer(p));
        }
    }

    private LinearLayout createLayoutForPlayer(Player p){
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setPadding(10,0,10,0);

        layout.addView(createNameTextView(p));
        layout.addView(createCurrentPar(p));
        layout.addView(createAddButton(p));
        layout.addView(createCounterTextView());
        layout.addView(createRemoveButton(p));
        layout.addView(createCurrentPoints(p));

        return layout;
    }
    private TextView createCurrentPar(Player p){
        TextView par = new TextView(getActivity());
        int [] plannedStrokes = p.getScores();
        par.setText(String.valueOf(plannedStrokes[viewModel.getCurrentHole()]));
        par.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        par.setTextSize(20);
        return par;
    }
    private TextView createCurrentPoints(Player p){
        TextView point = new TextView(getActivity());
        point.setText(String.valueOf(p.getTotalScore()));
        point.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        point.setTextSize(20);
        return point;
    }

    private TextView createNameTextView(Player p){
        TextView tv = new TextView(getActivity());
        tv.setText(p.getInitials());
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tv.setTextSize(30);
        return tv;
    }

    private Button createAddButton(Player p){
        Button b = new MaterialButton(getActivity());
        b.setText("add");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.incrementHole(holeNumber);
                updateUI();
            }
        });
        return b;
    }

    private TextView createCounterTextView(){
        TextView stat = new TextView(getActivity());
        stat.setText("0");
        stat.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        stat.setTextSize(60);
        counters.add(stat);
        return stat;
    }

    private Button createRemoveButton(Player p){
        Button b = new MaterialButton(getActivity());
        b.setText("remove");
        b.setPadding(0,0,0,0);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.decrementHole(holeNumber);
                updateUI();
            }
        });
        return b;
    }

    private void updateUI() {
        for(int p=0; p<scorecard.getPlayers().size(); p++){
            counters.get(p).setText(String.valueOf(scorecard.getPlayers().get(p).getShotsForHole(holeNumber)));
        }
    }
}
