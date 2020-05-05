package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.ui.oldGamesSelect.*;

import java.util.ArrayList;
import java.util.List;

public class ViewOldGamesActivity extends AppCompatActivity implements OldGamesRecyclerViewAdapter.ItemClickListener {

    private OldGamesRecyclerViewAdapter adapter;
    private List<Scorecard> oldGames = new ArrayList<>();
    private Repository repository;
    private boolean finishedGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_old_games);

        RecyclerView recyclerView = findViewById(R.id.rv_oldGames);
        adapter = new OldGamesRecyclerViewAdapter(this, oldGames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        repository = Repository.getRepository(this);

        finishedGame = getIntent().getExtras().getBoolean("finished", false);
        setOldGames();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setOldGames();
    }

    private void setOldGames() {
        new Thread(() -> {
            if (!finishedGame) {
                oldGames = repository.getDb().scorecardDAO().getUnfinishedRounds();
            } else {
                oldGames = repository.getDb().scorecardDAO().getFinishedRounds();
            }

            this.runOnUiThread(() -> {
                adapter.update(oldGames);
            });
        }).start();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent startGame = new Intent(getApplicationContext(), GameActivity.class);
        startGame.putExtra("scorecard", oldGames.get(position));
        startActivity(startGame);
    }
}
