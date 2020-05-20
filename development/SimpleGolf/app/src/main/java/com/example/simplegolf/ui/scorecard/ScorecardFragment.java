package com.example.simplegolf.ui.scorecard;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
    private ArrayList<ArrayList> awayFromPar;
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

        /*
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


         */

        viewModel.setShowStrokes(true);
        updateTable();
    }

    //Update to correct scores
    private void updateTable() {
        //Update score
        for (int player = 0; player < scorecard.getPlayers().size(); player++) {
            for (int hole = 0; hole < scorecard.getNumberOfHoles(); hole++) {
                if (viewModel.getShowStrokes()) {
                    ((TextView) scoreTextViews.get(player).get(hole)).setText(String.valueOf(scorecard.getPlayers().get(player).getShotsForHole(hole)));
                } else {
                    int score = scorecard.getPlayers().get(player).getScoreForHole(hole);
                    ((TextView) scoreTextViews.get(player).get(hole)).setText(String.valueOf(score));
                }

                //int h = scorecard.getPlayers().get(player).getShotsForHole(hole)-scorecard.getPlayers().get(player).getPlayerPar(hole);
                int h = scorecard.getPlayers().get(player).getScoreForHole(hole);

                if(h == 0) {
                    ((TextView) awayFromPar.get(player).get(hole)).setText(String.valueOf(h));
                    ((TextView) awayFromPar.get(player).get(hole)).setTextColor(Color.RED);
                } else if(h > 0){
                    ((TextView) awayFromPar.get(player).get(hole)).setText(String.valueOf(h));
                    ((TextView) awayFromPar.get(player).get(hole)).setTextColor(Color.BLUE);
                }
                /*
                if(h == 0){
                    ((TextView) awayFromPar.get(player).get(hole)).setText(String.valueOf(h));
                } */

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
        totalScoreTextViews = new ArrayList<>();
        scoreTextViews = new ArrayList<>();
        awayFromPar = new ArrayList<>();

        for(Player p: scorecard.getPlayers()){
            scoreTextViews.add(new ArrayList<TextView>());
            awayFromPar.add(new ArrayList<TextView>());
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
        int padding = Math.round(convertDpToPixel(8, getActivity()));
        row.setPadding(0, padding, 0, padding);

        row.addView(generateStaticTextView(R.string.hole, 20, 3f));
        row.addView(generateStaticTextView(R.string.par, 20, 3f));

        for(Player p: scorecard.getPlayers()){
            row.addView(generateStaticTextView(p.getInitials(), 20, (float) 12 / scorecard.getPlayers().size(), R.color.white));
        }

        return row;
    }

    private View makeHole(Hole h) {
        TableRow row = generateTableRow();

        row.setBackgroundResource(R.drawable.table_row_bg1);
        row.addView(generateStaticTextView(String.valueOf(h.getHoleNumber() + 1), 50, 3f, R.color.black));
        row.addView(generateStaticTextView(String.valueOf(h.getPar()), 30, 3f, R.color.black));

        for(int i = 0; i < scorecard.getPlayers().size(); i++){
            LinearLayout scoreStats = new LinearLayout(getActivity());

            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, (float) 12 / scorecard.getPlayers().size());
            scoreStats.setLayoutParams(params);
            scoreStats.setOrientation(LinearLayout.VERTICAL);

            //Params for textviews inside linearlayout
            params= new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            TextView tv = generateStaticTextView("0", 32, 0, R.color.black);
            tv.setLayoutParams(params);
            scoreStats.addView(tv);

            scoreTextViews.get(i).add(tv);

            tv = generateStaticTextView("0", 20, 0, R.color.black);
            tv.setLayoutParams(params);
            scoreStats.addView(tv);

            awayFromPar.get(i).add(tv);

            row.addView(scoreStats);
        }

        return row;
    }

    private View makeBottom() {
        TableRow row = generateTableRow();

        row.setBackgroundResource(R.drawable.table_row_bottom);
        row.addView(generateStaticTextView(R.string.total, 20, 6f));
        int padding = Math.round(convertDpToPixel(8, getActivity()));
        row.setPadding(0, padding, 0, padding);

        for(Player p: scorecard.getPlayers()){
            TextView tv = generateStaticTextView("0", 20, (float) 12 / scorecard.getPlayers().size(), R.color.white);
            totalScoreTextViews.add(tv);
            row.addView(tv);
        }

        return row;
    }

    private TableRow generateTableRow(){
        TableRow row = new TableRow(getActivity());

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 18f);
        row.setLayoutParams(params);

        return row;
    }

    private TextView generateStaticTextView(String text, int size, float weight, int color){
        TextView tv = new TextView(getActivity());
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, weight);

        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tv.setTextSize(size);
        tv.setText(text);
        tv.setTextColor(ContextCompat.getColor(getActivity(), color));

        return tv;
    }

    private TextView generateStaticTextView(int text, int size, float weight){
        TextView tv = new TextView(getActivity());
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, weight);

        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tv.setTextSize(size);
        tv.setText(text);
        tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        return tv;
    }

    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
