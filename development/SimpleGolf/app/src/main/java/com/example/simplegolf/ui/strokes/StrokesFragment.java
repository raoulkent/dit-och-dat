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

import com.example.simplegolf.R;

public class StrokesFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_HOLE = "holeNumber";
    private int holeNumber;

    Button add, remove;
    TextView counter;
    int hole=0;

    /**
     * Factory method to create new instances of this fragment
     * @param holeNumber
     * @return A new instance of StrokesFragment
     */
    public static StrokesFragment newInstance(int holeNumber) {
        StrokesFragment fragment = new StrokesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_HOLE, holeNumber);
        fragment.setArguments(args);
        Log.d("SWIPER", "FRAGMENT NEWINSTANCE" + holeNumber);
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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("SWIPER", "ON CREATE VIEW");

        return inflater.inflate(R.layout.fragment_strokes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        remove = view.findViewById(R.id.remove);
        add = view.findViewById(R.id.add);                       // Stroke counter for p1
        counter = view.findViewById(R.id.counter);
        add.setOnClickListener(this);
        remove.setOnClickListener(this);

        Log.d("SWIPER", "VIEW CREATED");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add: {
                hole++;
                counter.setText(String.valueOf(hole));
                break;
            }
            case R.id.remove: {
                hole--;
                counter.setText(String.valueOf(hole));
                break;
            }
        }
    }
}
