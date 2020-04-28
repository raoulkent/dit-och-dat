package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.testGames.TestGame;
import com.example.simplegolf.ui.oldGamesSelect.*;

import java.util.ArrayList;

public class ViewOldGamesActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    MyRecyclerViewAdapter adapter;
    ArrayList<Scorecard> oldGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_old_games);

        setOldGames(getIntent().getExtras().getBoolean("finished", false));

        RecyclerView recyclerView = findViewById(R.id.rv_oldGames);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, oldGames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private void setOldGames(Boolean finished){
        //TODO: Fetch data from DB
        if(finished){
            oldGames = new ArrayList<Scorecard>();
            oldGames.add(TestGame.INSTANCE.getGame());
        }else{
            oldGames = new ArrayList<Scorecard>();
            oldGames.add(TestGame.INSTANCE.getGame());
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent startGame = new Intent(getApplicationContext(), GameOverview.class);
        startGame.putExtra("scorecard",oldGames.get(position));
        startActivity(startGame);
    }
}
