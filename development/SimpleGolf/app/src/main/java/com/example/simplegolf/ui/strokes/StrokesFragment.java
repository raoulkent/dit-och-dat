package com.example.simplegolf.ui.strokes;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.simplegolf.R;

public class StrokesFragment extends Fragment implements View.OnClickListener {
    Button add, remove;
    TextView counter;
    int hole=0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_strokes, container, false);

        remove = (Button) root.findViewById(R.id.remove);
        add = (Button) root.findViewById(R.id.add);                       // Stroke counter for p1
        counter = (TextView) root.findViewById(R.id.counter);

        add.setOnClickListener(this);
        remove.setOnClickListener(this);
        return root;
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
