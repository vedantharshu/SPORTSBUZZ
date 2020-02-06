package com.example.scorecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cricket extends AppCompatActivity {
    EditText et1;
    EditText et2;
    EditText et3;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        bt=(Button)findViewById(R.id.bt);
        final String[] st={et1.getText().toString().trim(),et2.getText().toString().trim(),et3.getText().toString().trim()};


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("playerinfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("team1",et1.getText().toString().trim());
                editor.putString("team2",et2.getText().toString().trim());
                editor.putString("over",et3.getText().toString().trim());
                if (et1.getText().toString().trim().equalsIgnoreCase("")) {
                    et1.setError("This Field Cannot Be Blank");
                }
                else if (et2.getText().toString().trim().equalsIgnoreCase("")) {
                    et2.setError("This Field Cannot Be Blank");
                }
                 else if(Integer.parseInt(et3.getText().toString())>20)
                {
                    et3.setError("Overs Cant't Exceed 20");
                }
                else if((et3.getText().toString())=="")
                {
                    et3.setError("Enter Overs");
                }
                 else if(et2.getText().toString().trim().equalsIgnoreCase(et1.getText().toString().trim()))
                {
                    Toast tt=Toast.makeText(getApplicationContext(),"Team Name Can't Be Same",Toast.LENGTH_LONG);

                    tt.show();
                }
                 else
                {
                    Intent i =new Intent(Cricket.this,CricketScore.class);

                    i.putExtra("team1",et1.getText().toString().trim());
                    i.putExtra("team2",et2.getText().toString().trim());
                    i.putExtra("over",et3.getText().toString().trim());
                    startActivity(i);
                }
            }
        });
    }
}
