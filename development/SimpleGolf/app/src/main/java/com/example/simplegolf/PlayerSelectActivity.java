package com.example.simplegolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.Tee;
import com.example.simplegolf.ui.playerSelect.AddPlayerDialogue;
import com.example.simplegolf.ui.playerSelect.PlayerListAdapter;
import com.example.simplegolf.ui.playerSelect.PlayerSelectViewModel;

public class PlayerSelectActivity extends AppCompatActivity implements AddPlayerDialogue.DialogListener {

    private PlayerSelectViewModel viewModel;
    private RecyclerView recyclerView;
    private PlayerListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);
        viewModel = new ViewModelProvider(this).get(PlayerSelectViewModel.class);

        if (getIntent().hasExtra("course")) {
            Course course = (Course) getIntent().getSerializableExtra("course");
            viewModel.setCourse(course);
        }

        buildRecyclerView();
    }

    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.player_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new PlayerListAdapter(viewModel, this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void showDialog(View view) {
        AddPlayerDialogue addPlayerDialogue = new AddPlayerDialogue();
        addPlayerDialogue.show(getSupportFragmentManager(), "game activity dialog");
    }

    public void onClickCreateScorecard(View view) {
        Scorecard scorecard = viewModel.buildScorecard();

        // Save to DB and fetch to get correct ID, allows course to be updated/saved in the future.
        new Thread(() -> {
            Repository repository = Repository.getRepository(this);

            long scoreCardId = repository.getDb().scorecardDAO().insert(scorecard); // Save to DB
            Scorecard savedScorecard = repository.getDb().scorecardDAO().getById(scoreCardId); // Fetch from DB to include the new autogenerated ID.

            runOnUiThread(() -> { // Start activity after DB operations are done.
                Intent startGame = new Intent(getApplicationContext(), GameActivity.class);
                startGame.putExtra("scorecard", savedScorecard);
                finish();
                startActivity(startGame);
            });
        }).start();
    }

    @Override
    public void applyPlayerInfo(String name, String abbr, double hcp, Tee tee) {
        Course course = viewModel.getCourse();

        viewModel.addPlayer(new Player(name, abbr, course, tee, hcp));

        adapter.notifyDataSetChanged();
    }
}
