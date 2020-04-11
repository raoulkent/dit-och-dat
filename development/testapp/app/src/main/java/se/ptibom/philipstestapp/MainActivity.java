package se.ptibom.philipstestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Count count;
    private TextView textCounter;
    public static final String EXTRA_COUNT = "Counter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Beginning of Activity
        count = new Count("I'm a Count object.");
        textCounter = findViewById(R.id.textCounter);
        textCounter.setText(count.getCounter());
    }

    public void countUp(View view) {
        count.countUp();
        textCounter.setText(count.getCounter());
    }

    public void nextPage(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_COUNT, count);
        startActivity(intent);
    }
}
