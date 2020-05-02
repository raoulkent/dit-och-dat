package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.Tee;
import com.example.simplegolf.model.testcourses.TestCourses;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements GameActivityDialog.DialogListener {

    public static String N_HOLES = "nHoles";

    private List<Player> players = new ArrayList<>();

    private Scorecard scorecard;
    private Course course;

    private LinearLayout playerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerList = findViewById(R.id.playerList);


    }

    public void showDialog(View view){
        GameActivityDialog gameActivityDialog = new GameActivityDialog();
        gameActivityDialog.show(getSupportFragmentManager(), "game activity dialog");
    }

    public void onClickSelectCourse(View view) {
        Intent selectCourse = new Intent(getApplicationContext(), CourseSelectActivity.class);
        selectCourse.putExtra("current_course", course);
        startActivity(selectCourse);
    }

    public void onClickCreate(View view) {

        // Old scorecard.
        // scorecard = new Scorecard(nrHoles);
        // scorecard.addPlayer(nameInput.getText().toString());

        //TODO: Add course dynamically. This is temporary.
        Course chalmersCourse = TestCourses.INSTANCE.getCourseChalmers();
        scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());

        for (Player x:players)
            scorecard.addPlayer(x.getName(), x.getInitials(), x.getTee(), x.getHcp());


        //Send holes to GameOverview for now, this will change to game object
        Intent startGame = new Intent(getApplicationContext(), GameOverview.class);
        startGame.putExtra("scorecard", scorecard);
        startActivity(startGame);
    }



    @Override
    public void applyPlayerInfo(String name, String abbr, double HCP, String tee) {


        Tee temp = null;
        for(Tee x: TestCourses.INSTANCE.getCourseChalmers().getTees()){
            if(x.getName().equals(tee))
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
