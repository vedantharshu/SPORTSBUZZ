package com.example.scorecard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class CricketUser extends AppCompatActivity {

    TextView t1,t2;
    DatabaseReference db,db1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket_user);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        db=FirebaseDatabase.getInstance().getReference().child("Cricket").child("vipss").child("team1");
        db1=FirebaseDatabase.getInstance().getReference().child("Cricket").child("vipss").child("team2");
    }


    @Override
    protected void onStart() {
        super.onStart();

       db.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String teamA=dataSnapshot.getValue(String.class);
               t1.setText(teamA);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

        db1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String teamB=dataSnapshot.getValue(String.class);
                t2.setText(teamB);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
