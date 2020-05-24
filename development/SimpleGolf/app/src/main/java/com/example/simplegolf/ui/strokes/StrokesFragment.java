package com.example.simplegolf.ui.strokes;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.Scorecard;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class StrokesFragment extends Fragment implements View.OnClickListener {

    private StrokesViewModel viewModel;
    private static final String ARG_HOLE = "holeNumber";
    private int holeNumber;

    private Scorecard scorecard;
    private TextView textViewCurrentPar;

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
        viewModel = new ViewModelProvider(requireActivity()).get(StrokesViewModel.class);
        repository = Repository.getRepository(requireActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_strokes, container, false);

        scorecard = (Scorecard) requireActivity().getIntent().getSerializableExtra("scorecard");
        counters = new ArrayList<>();
        points = new ArrayList<>();
        layout = root.findViewById(R.id.playerLayout);

        textViewCurrentPar = root.findViewById(R.id.currentPar);

        String currentPar = new StringBuilder()
                .append(this.getString(R.string.parColon))
                .append(getTextViewCurrentPar())
                .toString();

        textViewCurrentPar.setText(currentPar);

        TextView textViewCurrentHole = root.findViewById(R.id.holeNumber);

        String currentHole = new StringBuilder()
                .append(getString(R.string.hole))
                .append(" ")
                .append(holeNumber + 1)
                .toString();

        textViewCurrentHole.setText(currentHole);


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

    private String getTextViewCurrentPar() {
        return String.valueOf(scorecard.getCourse().getHoles().get(holeNumber).getPar());
    }

    private CardView createLayoutForPlayer(Player p) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);


        CardView card = new CardView(requireActivity());
        card.setLayoutParams(params);
        card.setRadius(convertDpToPixel(4, requireActivity()));
        card.setElevation(convertDpToPixel(2, requireActivity()));

        LinearLayout playerLayout = new LinearLayout(requireActivity());

        int margin = Math.round(convertDpToPixel(4, requireActivity()));
        params.setMargins(margin, margin, margin, margin);

        playerLayout.setOrientation(LinearLayout.VERTICAL);
        playerLayout.setLayoutParams(params);
        playerLayout.setPadding(margin, margin, margin, margin);

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
        TextView par = new TextView(requireActivity());

        String currentPar = new StringBuilder()
                .append(this.getString(R.string.parColon))
                .append(" ")
                .append(p.getPlayerPar(holeNumber))
                .toString();

        par.setText(currentPar);
        par.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        par.setTextSize(20);
        par.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));

        return par;
    }

    private TextView createCurrentPoints(Player p) {
        TextView point = new TextView(requireActivity());

        String currentPoints = new StringBuilder()
                .append(this.getString(R.string.total))
                .append(" ")
                .append(p.getTotalScore())
                .toString();

        point.setText(currentPoints);
        point.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        point.setTextSize(20);
        point.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));

        points.add(point);
        return point;
    }

    private TextView createNameTextView(Player p) {
        TextView tv = new TextView(requireActivity());
        tv.setText(p.getInitials());
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tv.setTextSize(30);
        tv.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));
        return tv;
    }

    private Button createAddButton(Player p) {
        Button b = new MaterialButton(requireActivity());

        b.setText(this.getString(R.string.add_one));
        b.setTextSize(20);
        b.setOnClickListener(v -> {
            p.incrementHole(holeNumber);
            updateUI();
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margins = Math.round(convertDpToPixel(4, requireActivity()));
        params.setMargins(0, margins, 0, margins);
        params.gravity = Gravity.CENTER;
        int width = Math.round(convertDpToPixel(200, requireActivity()));
        b.setWidth(width);
        b.setLayoutParams(params);
        return b;
    }

    private TextView createCounterTextView() {
        TextView stat = new TextView(requireActivity());
        stat.setText(this.getString(R.string.par));
        stat.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        stat.setTextSize(60);
        stat.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));
        counters.add(stat);
        return stat;
    }

    private Button createRemoveButton(Player p) {
        Button b = new MaterialButton(requireActivity());
        b.setText(this.getString(R.string.remove_one));
        b.setTextSize(20);
        b.setOnClickListener(v -> {
            p.decrementHole(holeNumber);
            updateUI();
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margins = Math.round(convertDpToPixel(4, requireActivity()));
        params.setMargins(0, margins, 0, margins);
        params.gravity = Gravity.CENTER;
        int width = Math.round(convertDpToPixel(200, requireActivity()));
        b.setWidth(width);
        b.setLayoutParams(params);
        return b;
    }

    private static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void updateUI() {
        updateDB();
        updateCounters();
        updatePar();
    }

    private void updateDB() {
        new Thread(() -> repository.getDb().scorecardDAO().update(scorecard)).start();
    }

    private void updateCounters() {
        try {
            for (int p = 0; p < scorecard.getPlayers().size(); p++) {
                counters.get(p).setText(String.valueOf(scorecard.getPlayers().get(p).getShotsForHole(holeNumber)));

                String totalScore = new StringBuilder()
                        .append(this.getString(R.string.total))
                        .append("\n")
                        .append(scorecard.getPlayers().get(p).getTotalScore())
                        .toString();

                points.get(p).setText(totalScore);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Counters or Points are smaller than scorecards.getPlayers()");
        }
    }

    private void updatePar() {
        String parString = new StringBuilder()
                .append(this.getString(R.string.par))
                .append(" ")
                .append(getTextViewCurrentPar())
                .toString();

        textViewCurrentPar.setText(parString);
    }
}
