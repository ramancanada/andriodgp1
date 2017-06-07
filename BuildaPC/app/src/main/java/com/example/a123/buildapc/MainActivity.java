package com.example.a123.buildapc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (ImageButton) findViewById(R.id.imageButton3);
        bt2 = (ImageButton) findViewById(R.id.imageButton5);

    }

    public  void laptop(View v){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    public  void desktop(View v){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
}
