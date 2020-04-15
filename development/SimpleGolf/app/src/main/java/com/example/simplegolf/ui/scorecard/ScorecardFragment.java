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
import androidx.fragment.app.Fragment;

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
        //Values
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        //TODO: Calculate textSize and offset from resolution
        int textSize = 20;
        int offset = 30;
        int numPlayers = 1;
        int numHoles = getActivity().getIntent().getIntExtra("nHoles", 18);

        //Header
        TableRow row = new TableRow(getActivity());
        TextView tv = generateTextView(textSize, offset);
        tv.setText("Hole");
        tv.setBackgroundResource(R.drawable.table_row_left);
        row.addView(tv);

        //Add players to header
        for(int p = 1; p <= numPlayers; p++) {
            tv = generateTextView(textSize, offset);
            tv.setText("AK");
            tv.setBackgroundResource(R.drawable.table_row_bg);
            row.addView(tv);
        }
        mTable.addView(row, params);

        //Add rows for holes
        for(int i = 1; i <= numHoles; i++){
            row = new TableRow(getActivity());

            //Hole column
            tv = generateTextView(textSize, offset);
            tv.setText(String.valueOf(i));
            tv.setBackgroundResource(R.drawable.table_row_left);
            row.addView(tv);

            //Player columns
            for(int p = 1; p <= numPlayers; p++) {
                tv = generateTextView(textSize, offset);
                tv.setText("0");
                tv.setBackgroundResource(R.drawable.table_row_bg);
                row.addView(tv);
            }
            mTable.addView(row, params);
        }

        //Bottom row
        row = new TableRow(getActivity());
        for(int p = 0; p <= numPlayers; p++) {
            tv = generateTextView(textSize, offset);
            if (p == 0) {
                tv.setText("Tot:");
            }else{
                tv.setText("0");
                tv.setBackgroundResource(R.drawable.tabel_row_bottom);
            }
            row.addView(tv);
        }
        mTable.addView(row, params);
    }

    //Generate TextViews for generateTable method
    private TextView generateTextView(int textSize, int offset){
        TextView tv = new TextView(getActivity());
        tv.setTextSize(textSize);
        tv.setPadding(offset, 0, offset, 0);
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        return tv;
    }
}
