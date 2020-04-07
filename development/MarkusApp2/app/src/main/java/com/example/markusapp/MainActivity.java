package com.example.markusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button old, next, add, remove, add2, remove2, add3, remove3, add4, remove4;
    TextView holeNr, P1strokes, P2strokes, P3strokes, P4strokes;
    int hole=0;

    player p1 = new player();
    player p2 = new player();
    player p3 = new player();
    player p4 = new player();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        old = (Button) findViewById(R.id.buttonOld);                    // Counter for what hole
        next = (Button) findViewById(R.id.buttonNext);

        holeNr = (TextView) findViewById(R.id.Counter);

        add = (Button) findViewById(R.id.p1Add);                       // Stroke counter for p1
        remove = (Button) findViewById(R.id.p1Remove);
        add2 = (Button) findViewById(R.id.p2Add);                       // Stroke counter for p2
        remove2 = (Button) findViewById(R.id.p2Remove);
        add3 = (Button) findViewById(R.id.p3Add);                       // Stroke counter for p3
        remove3 = (Button) findViewById(R.id.p3Remove);
        add4 = (Button) findViewById(R.id.p4Add);                       // Stroke counter for p4
        remove4 = (Button) findViewById(R.id.p4Remove);

        P1strokes = (TextView) findViewById(R.id.p1Strokes);
        P2strokes = (TextView) findViewById(R.id.p2Strokes);
        P3strokes = (TextView) findViewById(R.id.p3Strokes);
        P4strokes = (TextView) findViewById(R.id.p4Strokes);

        old.setOnClickListener(this);
        next.setOnClickListener(this);
        add.setOnClickListener(this);
        remove.setOnClickListener(this);
        add2.setOnClickListener(this);
        remove2.setOnClickListener(this);
        add3.setOnClickListener(this);
        remove3.setOnClickListener(this);
        add4.setOnClickListener(this);
        remove4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonOld: {
                hole--;
                holeNr.setText(String.valueOf(hole));
                break;
            }
            case R.id.buttonNext: {
                hole++;
                holeNr.setText(String.valueOf(hole));
                break;
            }
            case R.id.p1Add: {
                p1.addStroke();
                P1strokes.setText(String.valueOf(p1.strokes));
                break;
            }
            case R.id.p1Remove: {
                p1.removeStroke();
                P1strokes.setText(String.valueOf(p1.strokes));
                break;
            }
            case R.id.p2Add: {
                p2.addStroke();
                P2strokes.setText(String.valueOf(p2.strokes));
                break;
            }
            case R.id.p2Remove: {
                p2.removeStroke();
                P2strokes.setText(String.valueOf(p2.strokes));
                break;
            }
            case R.id.p3Add: {
                p3.addStroke();
                P3strokes.setText(String.valueOf(p3.strokes));
                break;
            }
            case R.id.p3Remove: {
                p3.removeStroke();
                P3strokes.setText(String.valueOf(p3.strokes));
                break;
            }
            case R.id.p4Add: {
                p4.addStroke();
                P4strokes.setText(String.valueOf(p4.strokes));
                break;
            }
            case R.id.p4Remove: {
                p4.removeStroke();
                P4strokes.setText(String.valueOf(p4.strokes));
                break;
            }
        }

    }
}
class player {                                         // Creates class player with name, id, number of strokes. plus methods to add and remove strokes
    int playerNr;
    String playerName;
    int strokes=0;
    void addStroke() {
        this.strokes++;
    }
    void removeStroke() {
        this.strokes--;
    }
}