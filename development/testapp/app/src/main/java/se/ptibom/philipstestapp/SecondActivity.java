package se.ptibom.philipstestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Beginning of Activity
        Intent intent = getIntent();
        Count count = (Count)intent.getSerializableExtra(MainActivity.EXTRA_COUNT);
        TextView textPassed = findViewById(R.id.textPassed);
        TextView textValue = findViewById(R.id.textValue);

        textPassed.setText(count.getRandomText());
        textValue.setText(count.getCounter());
    }


    public void goBack(View view) {
        finish();
    }
}
