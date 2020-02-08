package com.example.scorecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Sports_User extends AppCompatActivity {

    ImageView cricket,badminton,football,basketball,tennis,volleyball;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports__user);
        cricket=(ImageView)findViewById(R.id.imageView1);
        badminton=(ImageView)findViewById(R.id.imageView2);
        football=(ImageView)findViewById(R.id.imageView3);
        basketball=(ImageView)findViewById(R.id.imageView4);
        tennis=(ImageView)findViewById(R.id.imageView5);
        volleyball=(ImageView)findViewById(R.id.imageView6);

        cricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cr=new Intent(Sports_User.this,CricketUser.class);
                startActivity(cr);
            }
        });
        badminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bad=new Intent(Sports_User.this,BadmintonUser.class);
                startActivity(bad);
            }
        });
        football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ft=new Intent(Sports_User.this,FootballUser.class);
                startActivity(ft);
            }
        });
        basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bb=new Intent(Sports_User.this,BasketballUser.class);
                startActivity(bb);
            }
        });
        tennis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tn=new Intent(Sports_User.this,TennisUser.class);
                startActivity(tn);
            }
        });
        volleyball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vb=new Intent(Sports_User.this,VolleyballUser.class);
                startActivity(vb);
            }
        });
    }
}
