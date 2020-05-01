package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.testcourses.TestCourses;
import com.google.android.material.card.MaterialCardView;

public class GameActivity extends AppCompatActivity implements GameActivityDialog.DialogListener {

    public static String N_HOLES = "nHoles";

    private Scorecard scorecard;
    private Course course;

    private RecyclerView recViewPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        recViewPlayers = findViewById(R.id.recViewPlayers);


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
        scorecard.addPlayer("Test", chalmersCourse, chalmersCourse.getTees().get(2), 36.0);

        //Send holes to GameOverview for now, this will change to game object
        Intent startGame = new Intent(getApplicationContext(), GameOverview.class);
        startGame.putExtra("scorecard", scorecard);
        startActivity(startGame);
    }



    @Override
    public void applyPlayerInfo(String name, String abbr, double HCP, String tee) {

        MaterialCardView playerCard = new MaterialCardView(this);

        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );

        playerCard.setLayoutParams(params);

        playerCard.setContentPadding(15,15,15,15);

        // Set CardView corner radius
        playerCard.setRadius(9);

        // Set cardView content padding


        // Set a background color for CardView
        playerCard.setCardBackgroundColor(Color.parseColor("#FFC6D6C3"));

        // Set the CardView maximum elevation
        playerCard.setMaxCardElevation(15);

        // Set CardView elevation
        playerCard.setCardElevation(9);


        TextView tvName = new TextView(this);
       // tvName.setLayoutParams(params);
        tvName.setLayoutParams(params);
        tvName.setText(name);


        playerCard.addView(tvName);

        recViewPlayers.addView(tvName);




    }
}
