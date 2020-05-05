package com.example.simplegolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.Tee;
import com.example.simplegolf.model.testcourses.TestCourses;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectActivity extends AppCompatActivity implements PlayerSelectDialogue.DialogListener {

    private List<Player> players = new ArrayList<>();

    private Scorecard scorecard;
    private Course course;

    private LinearLayout playerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);

        playerList = findViewById(R.id.playerList);


    }

    public void showDialog(View view) {
        PlayerSelectDialogue playerSelectDialogue = new PlayerSelectDialogue();
        playerSelectDialogue.show(getSupportFragmentManager(), "game activity dialog");
    }

    public void onClickSelectCourse(View view) {
        Intent goSelectCourse = new Intent(getApplicationContext(), CourseSelectActivity.class);
        goSelectCourse.putExtra("current_course", course);
        startActivity(goSelectCourse);
    }

    public void onClickCreate(View view) {

        // Old scorecard.
        // scorecard = new Scorecard(nrHoles);
        // scorecard.addPlayer(nameInput.getText().toString());

        //TODO: Add course dynamically. This is temporary.
        Course chalmersCourse = TestCourses.INSTANCE.getCourseChalmers();
        scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());
        scorecard.addPlayers(players);
        /*
        for (Player player : players) {
            scorecard.addPlayer(player.getName(), player.getInitials(), player.getTee(), player.getHcp());
        } */

        // Save to DB and fetch to get correct ID, allows course to be updated/saved in the future.
        new Thread(() -> {
            Repository repository = Repository.getRepository(this);

            long scoreCardId = repository.getDb().scorecardDAO().insert(scorecard); // Save to DB
            Scorecard savedScorecard = repository.getDb().scorecardDAO().getById(scoreCardId); // Fetch from DB to include the new autogenerated ID.

            runOnUiThread(() -> { // Start activity after DB operations are done.
                Intent startGame = new Intent(getApplicationContext(), GameActivity.class);
                startGame.putExtra("scorecard", savedScorecard);
                startActivity(startGame);
            });
        }).start();
    }


    @Override
    public void applyPlayerInfo(String name, String abbr, double HCP, String tee) {


        Tee temp = null;
        for (Tee x : TestCourses.INSTANCE.getCourseChalmers().getTees()) {
            if (x.getName().equals(tee))
                temp = x;
        }

        players.add(new Player(name, abbr, TestCourses.INSTANCE.getCourseChalmers(), temp, HCP));


        TextView tv = new TextView(this);
        tv.setText(name);
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tv.setTextSize(40);

        playerList.addView(tv);


    }
}