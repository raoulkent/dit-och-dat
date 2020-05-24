package com.example.simplegolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.ui.gamehistory.GameHistoryAdapter;
import com.example.simplegolf.ui.gamehistory.GameHistoryViewModel;

import java.util.Objects;

public class GameHistoryActivity extends AppCompatActivity implements GameHistoryAdapter.ItemClickListener {

    private GameHistoryAdapter adapter;
    private GameHistoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);
        boolean completedGames = Objects.requireNonNull(getIntent().getExtras()).getBoolean("finished", false);

        viewModel = new GameHistoryViewModel(this);
        viewModel.setCompletedGames(completedGames);

        RecyclerView recyclerView = findViewById(R.id.rv_oldGames);
        adapter = new GameHistoryAdapter(viewModel, this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(completedGames){
            this.setTitle(R.string.finished_game_btn);
        } else {
            this.setTitle(R.string.unfinished_game_btn);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.loadGames(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        if(viewModel.getScorecards().get(position).isFinishedRound()) {
            Intent startGame = new Intent(getApplicationContext(), FinishedGameViewerActivity.class);
            startGame.putExtra("scorecard", viewModel.getScorecards().get(position));
            startActivity(startGame);
        } else {
            Intent startGame = new Intent(getApplicationContext(), GameActivity.class);
            startGame.putExtra("scorecard", viewModel.getScorecards().get(position));
            finish();
            startActivity(startGame);
        }
    }
}