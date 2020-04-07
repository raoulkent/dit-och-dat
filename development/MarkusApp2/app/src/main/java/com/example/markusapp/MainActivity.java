package com.example.markusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button old, next;
    TextView counter;
    int hole=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        old = (Button) findViewById(R.id.buttonOld);
        next = (Button) findViewById(R.id.buttonNext);
        counter = (TextView) findViewById(R.id.Counter);

        old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hole--;
                counter.setText(String.valueOf(hole));
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hole++;
                counter.setText(String.valueOf(hole));
            }
        });
    }
}
