package com.example.simplegolf.ui.scorecard;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.simplegolf.R;

public class ScorecardFragment extends Fragment {

    private TableLayout mTable;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_scorecard, container, false);
        mTable = (TableLayout)root.findViewById(R.id.scorecardTable);
        generateTable();


        return root;
    }

    private void generateTable(){
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        int textSize = 20;
        int offset = 30;
        int numPlayers = 3;

        TableRow row = new TableRow(getActivity());
        TextView tv = new TextView(getActivity());
        tv.setTextSize(textSize);
        tv.setText("Hole");
        tv.setPadding(offset,0,offset,0);
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        row.addView(tv);

        for(int p = 1; p <= numPlayers; p++) {
            tv = new TextView(getActivity());
            tv.setTextSize(textSize);
            tv.setText("AK");
            tv.setPadding(offset, 0, offset, 0);
            tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            row.addView(tv);
        }
        mTable.addView(row, params);

        for(int i = 1; i <= 18; i++){
            row = new TableRow(getActivity());

            tv = new TextView(getActivity());
            tv.setTextSize(textSize);
            tv.setText(String.valueOf(i));
            tv.setPadding(offset,0,offset,0);
            tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            row.addView(tv);

            for(int p = 1; p <= numPlayers; p++) {
                tv = new TextView(getActivity());
                tv.setTextSize(textSize);
                tv.setText("0");
                tv.setPadding(offset, 0, offset, 0);
                tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                row.addView(tv);
            }
            mTable.addView(row, params);
        }
    }
}
