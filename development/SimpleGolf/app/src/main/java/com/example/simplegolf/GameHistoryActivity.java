package com.example.simplegolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.model.Repository;
import com.example.simplegolf.ui.gamehistory.GameHistoryAdapter;
import com.example.simplegolf.ui.gamehistory.GameHistoryViewModel;

public class GameHistoryActivity extends AppCompatActivity implements GameHistoryAdapter.ItemClickListener {

    private GameHistoryAdapter adapter;
    private GameHistoryViewModel viewModel;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);
        boolean completedGames = getIntent().getExtras().getBoolean("finished", false);

        viewModel = new GameHistoryViewModel(this);
        viewModel.setCompletedGames(completedGames);

        RecyclerView recyclerView = findViewById(R.id.rv_oldGames);
        adapter = new GameHistoryAdapter(viewModel, this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.loadGames();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent startGame = new Intent(getApplicationContext(), GameActivity.class);
        startGame.putExtra("scorecard", viewModel.getScorecards().get(position));
        startActivity(startGame);
    }
}