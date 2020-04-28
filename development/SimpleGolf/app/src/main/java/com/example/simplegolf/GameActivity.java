package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.testcourses.TestCourses;

public class GameActivity extends AppCompatActivity implements GameActivityDialog.DialogListener {

    public static String N_HOLES = "nHoles";

    private Scorecard scorecard;
    private Course course;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


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

    }
}
