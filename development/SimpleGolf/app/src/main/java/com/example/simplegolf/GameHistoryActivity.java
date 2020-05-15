package com.example.simplegolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.model.Repository;
import com.example.simplegolf.ui.gameHistory.GameHistoryAdapter;
import com.example.simplegolf.ui.gameHistory.GameHistoryViewModel;

public class GameHistoryActivity extends AppCompatActivity implements GameHistoryAdapter.ItemClickListener {

    private GameHistoryAdapter adapter;
    private GameHistoryViewModel viewModel = new GameHistoryViewModel();
    private Repository repository;
    private boolean finishedGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);

        repository = Repository.getRepository(this);
        loadOldGames();

        RecyclerView recyclerView = findViewById(R.id.rv_oldGames);
        adapter = new GameHistoryAdapter(viewModel, this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        finishedGame = getIntent().getExtras().getBoolean("finished", false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadOldGames();
    }

    private void loadOldGames() {
        new Thread(() -> {
            if (!finishedGame) {
                viewModel.setScorecards(repository.getDb().scorecardDAO().getUnfinishedRounds());
            } else {
                viewModel.setScorecards(repository.getDb().scorecardDAO().getFinishedRounds());
            }

            this.runOnUiThread(() -> {
                adapter.update(viewModel);
            });
        }).start();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent startGame = new Intent(getApplicationContext(), GameActivity.class);
        startGame.putExtra("scorecard", viewModel.getScorecards().get(position));
        startActivity(startGame);
    }
}
