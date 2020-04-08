package com.example.myapplicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     TextView text ;
     EditText number1;
     EditText number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number1=findViewById(R.id.lol0);
        number2=findViewById(R.id.lol1);

    }

   public void onButtonClick(View v){



        int a = Integer.parseInt(number1.getText().toString());
       int b = Integer.parseInt(number2.getText().toString());


       Data data = new Data(a+b,"write .....");
       Intent intent = new Intent(this,Screen2.class);
       startActivity(intent);
   }
}
