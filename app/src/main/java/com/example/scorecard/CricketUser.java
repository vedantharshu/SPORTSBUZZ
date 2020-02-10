package com.example.scorecard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CricketUser extends AppCompatActivity {

    TextView t1,t2;
    ListView matchListView;
    DatabaseReference db,db1,dbMatch;
    List<Matches> matchesList;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket_user);

        matchesList=new ArrayList<>();

        matchListView=(ListView)findViewById(R.id.matchListView);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        db=FirebaseDatabase.getInstance().getReference().child("CricketCurrent").child("team1");
        db1=FirebaseDatabase.getInstance().getReference().child("CricketCurrent").child("team2");
        dbMatch=FirebaseDatabase.getInstance().getReference().child("Cricket");

        this.mHandler = new Handler();

        this.mHandler.postDelayed(m_Runnable,5000);
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            Toast.makeText(CricketUser.this,"in runnable", Toast.LENGTH_SHORT).show();

            CricketUser.this.mHandler.postDelayed(m_Runnable, 5000);
        }

    };//runnable


    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(m_Runnable);
        finish();

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

        dbMatch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                matchesList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    Matches ob=ds.getValue(Matches.class);
                    matchesList.add(ob);
                }
                TeamList adapter =new TeamList(CricketUser.this, matchesList);
                matchListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
