package com.example.scorecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Football extends AppCompatActivity {

    Button fb_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);

        fb_btn=(Button)findViewById(R.id.fb_btn);

        fb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foot_score =new Intent(Football.this,football_score.class);
                startActivity(foot_score);
            }
        });

    }
}
