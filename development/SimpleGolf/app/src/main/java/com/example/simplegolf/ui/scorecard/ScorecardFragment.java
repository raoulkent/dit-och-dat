package com.example.simplegolf.ui.scorecard;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class ScorecardFragment extends Fragment {

    private TableLayout mTable;
    private ArrayList<ArrayList> scoreTextViews;
    private ArrayList<TextView> totalScoreTextViews;
    private RadioGroup selector;
    private Scorecard scorecard;
    private ScorecardViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ScorecardViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_scorecard, container, false);

        mTable = root.findViewById(R.id.scorecardTable);
        scorecard = (Scorecard) getActivity().getIntent().getSerializableExtra("scorecard");

        generateTable();
        setupSelector(root);
        updateTable();

        return root;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateTable();
    }

    private void setupSelector(View root){
        this.selector = root.findViewById(R.id.gameTypeRadioGroup);

        if(viewModel.getShowStrokes()){
            selector.check(R.id.strokesRadioButton);
        }else{
            selector.check(R.id.pointsRadioButton);
        }

        selector.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.strokesRadioButton){
                viewModel.setShowStrokes(true);
            }else if (checkedId == R.id.pointsRadioButton){
                viewModel.setShowStrokes(false);
            }
            updateTable();
        });
    }

    //Update to correct scores
    private void updateTable() {
        //Update score
        for (int p = 0; p < scorecard.getPlayers().size(); p++) {
            for(int h = 0; h < scorecard.getNumberOfHoles(); h ++)
                if(viewModel.getShowStrokes()) {
                    ((TextView) scoreTextViews.get(p).get(h)).setText(String.valueOf(scorecard.getPlayers().get(p).getShotsForHole(h)));
                }else{
                    //TODO: Get points
                    ((TextView) scoreTextViews.get(p).get(h)).setText("0");
                }
        }
        //Update total
        for (int p = 0; p < scorecard.getPlayers().size(); p++) {
            if(viewModel.getShowStrokes()) {
                totalScoreTextViews.get(p).setText(String.valueOf( scorecard.getPlayers().get(p).getTotalShots() ));
            }else{
                //TODO: Get total points
                totalScoreTextViews.get(p).setText("10");
            }
        }
    }

    private void generateTable() {
        //Values
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

        //Create ArrayLists for textViews
        totalScoreTextViews = new ArrayList<TextView>();
        scoreTextViews = new ArrayList<ArrayList>();
        for (int p = 1; p <= Objects.requireNonNull(scorecard).getPlayers().size(); p++) {
            scoreTextViews.add(new ArrayList<TextView>());
        }

        mTable.addView(makeHeaderRow(), params);

        for (int holeNum = 1; holeNum <= Objects.requireNonNull(scorecard).getHoles().size(); holeNum++) {
            TableRow holeRow = makeHoleRow(holeNum);
            mTable.addView(holeRow, params);
        }

        mTable.addView(makeBottomRow(scorecard), params);
    }

    private TableRow makeHeaderRow() {
        TableRow row = new TableRow(getActivity());
        TextView tv = generateTextView();
        tv.setText(R.string.hole);
        tv.setBackgroundResource(R.drawable.table_row_left);
        row.addView(tv);

        //TODO Change false to (if par exists)
        if(false){
            tv = generateTextView();
            tv.setText(R.string.par);
            tv.setBackgroundResource(R.drawable.table_row_bg);
            row.addView(tv);
        }

        ArrayList<Player> players = scorecard.getPlayers();
        for (Player p : players) {
            tv = generateTextView();
            tv.setText(p.getInitials());
            tv.setBackgroundResource(R.drawable.table_row_bg);
            row.addView(tv);
        }
        return row;
    }

    private TableRow makeHoleRow(int holeNumber) {
        TableRow row = new TableRow(getActivity());

        //Hole column
        TextView tv = generateTextView();
        tv.setText(String.valueOf(holeNumber));
        tv.setBackgroundResource(R.drawable.table_row_left);
        row.addView(tv);

        //TODO Change false to (if par exists)
        if(false){
            tv = generateTextView();
            //TODO fetch par from scorecard
            tv.setText("0");
            tv.setBackgroundResource(R.drawable.table_row_bg);
            row.addView(tv);
        }

        //Player columns
        for (int p = 0; p < Objects.requireNonNull(scorecard).getPlayers().size(); p++) {
            tv = generateTextView();
            scoreTextViews.get(p).add(tv);
            tv.setText("0");
            tv.setBackgroundResource(R.drawable.table_row_bg);
            row.addView(tv);
        }
        return row;
    }

    private TableRow makeBottomRow(Scorecard scorecard) {
        TableRow row = new TableRow(getActivity());

        TextView tv = generateTextView();
        tv.setText("Tot:");
        row.addView(tv);

        //TODO Change false to (if par exists)
        if(false){
            tv = generateTextView();
            //TODO fetch total par from scorecard
            tv.setText("0");
            tv.setBackgroundResource(R.drawable.tabel_row_bottom);
            row.addView(tv);
        }

        for (int p = 0; p < Objects.requireNonNull(scorecard).getPlayers().size(); p++) {
            tv = generateTextView();
            totalScoreTextViews.add(tv);
            tv.setText("0");
            tv.setBackgroundResource(R.drawable.tabel_row_bottom);
            row.addView(tv);
        }
        return row;
    }

    //Generate TextViews for generateTable method
    private TextView generateTextView() {
        //TODO: Calculate textSize and offset from resolution
        int textSize = 18;
        int offset = 30;

        TextView tv = new TextView(getActivity());
        tv.setTextSize(textSize);
        tv.setPadding(offset, 0, offset, 0);
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        return tv;
    }
}
