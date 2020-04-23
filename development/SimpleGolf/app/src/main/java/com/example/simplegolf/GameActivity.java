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

import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;

public class GameActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    public static String N_HOLES = "nHoles";

    private Scorecard scorecard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        NumberPicker nrHolesPicker = findViewById(R.id.nrHolesPicker);
        nrHolesPicker.setMinValue(1);
        nrHolesPicker.setMaxValue(18);
        nrHolesPicker.setValue(9);
        nrHolesPicker.setOnValueChangedListener(this);


        // TODO: Clean up this mess and make it dynamic.
        //  Also remove the comments from below once they are not needed.

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = findViewById(R.id.autocomplete_course);
        // Get the string array
        String[] courses = getResources().getStringArray(R.array.courses_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);
        textView.setAdapter(adapter);
    }

    public void onClickCreate(View view) {
        NumberPicker nrHolesPicker = findViewById(R.id.nrHolesPicker);
        EditText nameInput = findViewById(R.id.editTextName);
        int nrHoles = nrHolesPicker.getValue();

        scorecard = new Scorecard(nrHoles);
        scorecard.addPlayer(nameInput.getText().toString());

        //Send holes to GameOverview for now, this will change to game object
        Intent startGame = new Intent(getApplicationContext(), GameOverview.class);
        startGame.putExtra("scorecard", scorecard);
        startActivity(startGame);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }
}
