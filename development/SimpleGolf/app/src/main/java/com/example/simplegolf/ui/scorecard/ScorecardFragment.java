package com.example.simplegolf.ui.scorecard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.simplegolf.R;

public class ScorecardFragment extends Fragment {

    private ScorecardViewModel scorecardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scorecardViewModel =
                ViewModelProviders.of(this).get(ScorecardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scorecard, container, false);
        final TextView textView = root.findViewById(R.id.text_scorecard);
        scorecardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
