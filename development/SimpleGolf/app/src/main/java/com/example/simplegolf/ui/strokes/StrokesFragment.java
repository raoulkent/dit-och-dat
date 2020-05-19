package com.example.simplegolf.ui.strokes;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Hole;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.Scorecard;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class StrokesFragment extends Fragment implements View.OnClickListener {

    private StrokesViewModel viewModel;
    private static final String ARG_HOLE = "holeNumber";
    private int holeNumber;

    private Scorecard scorecard;
    private TextView currentPar;

    private LinearLayout layout;
    private ArrayList<TextView> counters;
    private ArrayList<TextView> points;

    private Repository repository;

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
        repository = Repository.getRepository(getActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_strokes, container, false);

        scorecard = (Scorecard) requireActivity().getIntent().getSerializableExtra("scorecard");
        counters = new ArrayList<>();
        points = new ArrayList<>();
        layout = root.findViewById(R.id.playerLayout);

        currentPar = root.findViewById(R.id.CurrentPar);
        currentPar.setText( this.getString(R.string.parColon) + getCurrentPar());


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

    private void addPlayers(View root) {
        for (Player p : scorecard.getPlayers()) {
            layout.addView(createLayoutForPlayer(p));
        }
    }
    private String getCurrentPar() {
        return String.valueOf(scorecard.getCourse().getHoles().get(holeNumber).getPar());
    }

    private CardView createLayoutForPlayer(Player p) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1);

        CardView card = new CardView(getActivity());
        card.setLayoutParams(params);
        card.setRadius(convertDpToPixel(4, getActivity()));
        card.setElevation(convertDpToPixel(2, getActivity()));

        LinearLayout playerLayout = new LinearLayout(getActivity());
        params.setMargins(5,0,5,0);

        playerLayout.setOrientation(LinearLayout.VERTICAL);
        playerLayout.setLayoutParams(params);
        playerLayout.setPadding(5, 0, 5, 0);

        playerLayout.addView(createNameTextView(p));
        playerLayout.addView(createCurrentPar(p));
        playerLayout.addView(createAddButton(p));
        playerLayout.addView(createCounterTextView());
        playerLayout.addView(createRemoveButton(p));
        playerLayout.addView(createCurrentPoints(p));

        card.addView(playerLayout);

        return card;
    }

    private TextView createCurrentPar(Player p) {
        TextView par = new TextView(getActivity());
        par.setText(this.getString(R.string.parColon) +" "+ p.getPlayerPar(holeNumber));
        par.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        par.setTextSize(20);
        return par;
    }

    private TextView createCurrentPoints(Player p) {
        TextView point = new TextView(getActivity());
        point.setText(this.getString(R.string.total) +" "+ p.getTotalScore());
        point.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        point.setTextSize(20);
        points.add(point);
        return point;
    }

    private TextView createNameTextView(Player p) {
        TextView tv = new TextView(getActivity());
        tv.setText(p.getInitials());
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tv.setTextSize(30);
        return tv;
    }

    private Button createAddButton(Player p) {
        Button b = new MaterialButton(getActivity());
        b.setText(this.getString(R.string.add));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.incrementHole(holeNumber);
                updateUI();
            }
        });
        return b;
    }

    private TextView createCounterTextView() {
        TextView stat = new TextView(getActivity());
        stat.setText(this.getString(R.string.par));
        stat.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        stat.setTextSize(60);
        counters.add(stat);
        return stat;
    }

    private Button createRemoveButton(Player p) {
        Button b = new MaterialButton(getActivity());
        b.setText(this.getString(R.string.remove));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.decrementHole(holeNumber);
                updateUI();
            }
        });
        return b;
    }

    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void updateUI() {
        updateDB();
        for (int p = 0; p < scorecard.getPlayers().size(); p++) {
            counters.get(p).setText(String.valueOf(scorecard.getPlayers().get(p).getShotsForHole(holeNumber)));
            points.get(p).setText(this.getString(R.string.total) + " " + scorecard.getPlayers().get(p).getTotalScore());
        }
        currentPar.setText(this.getString(R.string.par) +" "+ getCurrentPar());
    }

    // Saves new scores etc.
    private void updateDB() {
        new Thread(() -> {
            repository.getDb().scorecardDAO().update(scorecard);
        }).start();
    }
}
