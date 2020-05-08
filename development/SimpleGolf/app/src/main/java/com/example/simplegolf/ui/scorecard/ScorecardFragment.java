package com.example.simplegolf.ui.scorecard;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Hole;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;

import java.util.ArrayList;

public class ScorecardFragment extends Fragment {

    private TableLayout header;
    private TableLayout holes;
    private TableLayout bottom;
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

        header = root.findViewById(R.id.scorecardTableHeader);
        holes = root.findViewById(R.id.scorecardTableHole);
        bottom = root.findViewById(R.id.scorecardTableBottom);

        scorecard = (Scorecard) getActivity().getIntent().getSerializableExtra("scorecard");

        createTable();
        setupSelector(root);
        updateTable();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateTable();
    }

    private void setupSelector(View root) {
        this.selector = root.findViewById(R.id.gameTypeRadioGroup);

        if (viewModel.getShowStrokes()) {
            selector.check(R.id.strokesRadioButton);
        } else {
            selector.check(R.id.pointsRadioButton);
        }

        selector.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.strokesRadioButton) {
                viewModel.setShowStrokes(true);
            } else if (checkedId == R.id.pointsRadioButton) {
                viewModel.setShowStrokes(false);
            }
            updateTable();
        });
    }

    //Update to correct scores
    private void updateTable() {
        //Update score
        for (int p = 0; p < scorecard.getPlayers().size(); p++) {
            for (int h = 0; h < scorecard.getNumberOfHoles(); h++)
                if (viewModel.getShowStrokes()) {
                    ((TextView) scoreTextViews.get(p).get(h)).setText(String.valueOf(scorecard.getPlayers().get(p).getShotsForHole(h)));
                } else {
                    int score = scorecard.getPlayers().get(p).getScoreForHole(h);
                    ((TextView) scoreTextViews.get(p).get(h)).setText(String.valueOf(score));
                }
        }
        //Update total
        for (int p = 0; p < scorecard.getPlayers().size(); p++) {
            if (viewModel.getShowStrokes()) {
                totalScoreTextViews.get(p).setText(String.valueOf(scorecard.getPlayers().get(p).getTotalShots()));
            } else {
                int totalScore = scorecard.getPlayers().get(p).getTotalScore();
                totalScoreTextViews.get(p).setText(String.valueOf(totalScore));
            }
        }
    }

    private void createTable(){
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        totalScoreTextViews = new ArrayList<TextView>();
        scoreTextViews = new ArrayList<ArrayList>();

        for(Player p: scorecard.getPlayers()){
            scoreTextViews.add(new ArrayList<TextView>());
        }

        header.addView(makeHeader(), params);

        for(Hole h: scorecard.getHoles()){
            holes.addView(makeHole(h), params);
        }

        bottom.addView(makeBottom(), params);
    }

    private View makeHeader() {
        TableRow row = generateTableRow();

        row.setBackgroundResource(R.drawable.table_row_header);
        row.addView(generateStaticTextView(R.string.hole, 20, 2f));
        row.addView(generateStaticTextView(R.string.par, 20, 2f));

        for(Player p: scorecard.getPlayers()){
            row.addView(generateStaticTextView(p.getInitials(), 20, (float) 12 / scorecard.getPlayers().size()));
        }

        return row;
    }

    private View makeHole(Hole h) {
        TableRow row = generateTableRow();

        row.setBackgroundResource(R.drawable.table_row_bg1);
        row.addView(generateStaticTextView(String.valueOf(h.getHoleNumber() + 1), 30, 2f));
        row.addView(generateStaticTextView(String.valueOf(h.getPar()), 30, 2f));

        for(int i = 0; i < scorecard.getPlayers().size(); i++){
            TextView tv = generateStaticTextView("0", 30, (float) 12 / scorecard.getPlayers().size());
            scoreTextViews.get(i).add(tv);
            row.addView(tv);
        }

        return row;
    }

    private View makeBottom() {
        TableRow row = generateTableRow();

        row.setBackgroundResource(R.drawable.table_row_bottom);
        row.addView(generateStaticTextView(R.string.total, 20, 4f));

        for(Player p: scorecard.getPlayers()){
            TextView tv = generateStaticTextView("0", 20, (float) 12 / scorecard.getPlayers().size());
            totalScoreTextViews.add(tv);
            row.addView(tv);
        }

        return row;
    }

    private TableRow generateTableRow(){
        TableRow row = new TableRow(getActivity());

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 16f);
        row.setLayoutParams(params);

        return row;
    }

    private TextView generateStaticTextView(String text, int size, float weight){
        TextView tv = new TextView(getActivity());
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, weight);

        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tv.setTextSize(size);
        tv.setText(text);

        return tv;
    }

    private TextView generateStaticTextView(int text, int size, float weight){
        TextView tv = new TextView(getActivity());
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, weight);

        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tv.setTextSize(size);
        tv.setText(text);

        return tv;
    }

}
