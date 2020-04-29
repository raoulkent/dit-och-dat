package com.example.simplegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.testcourses.TestCourses;

public class GameActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    public static String N_HOLES = "nHoles";

    private Scorecard scorecard;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        NumberPicker nrHolesPicker = findViewById(R.id.nrHolesPicker);
        nrHolesPicker.setMinValue(1);
        nrHolesPicker.setMaxValue(18);
        nrHolesPicker.setValue(9);
        nrHolesPicker.setOnValueChangedListener(this);
    }

    public void onClickSelectCourse(View view) {
        Intent goSelectCourse = new Intent(getApplicationContext(), CourseSelectActivity.class);
        goSelectCourse.putExtra("current_course", course);
        startActivity(goSelectCourse);
    }

    public void onClickCreate(View view) {
        NumberPicker nrHolesPicker = findViewById(R.id.nrHolesPicker);
        EditText nameInput = findViewById(R.id.editTextName);
        int nrHoles = nrHolesPicker.getValue();

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
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }
}
