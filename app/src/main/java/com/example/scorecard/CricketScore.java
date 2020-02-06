package com.example.scorecard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CricketScore extends AppCompatActivity {
    TextView t1,t3,tv2,card,score,tv1,tv12,wkt,summary,btn;

    RadioButton cb1,cb2;

    Button one,two,three,four,five,six,wide,noball,wicket,zero;

    DatabaseReference mref;



    String s="",ss="",runs1="",wickets1="",st="",alternate="",current="",runs2="",wickets2="";

    boolean flag=false,isCheck=false;

    int runs=0,wkts=0,balls=0,div=0,rem=0,total,inningsOver=0,target;

    @Override
    public void onBackPressed() {
      new AlertDialog.Builder(this).setTitle("Really Exit?").setMessage("Pressing back will reset all data").setNegativeButton("Cancel",null).setPositiveButton("Continue", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
              CricketScore.super.onBackPressed();
          }
      }).create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket_score);

        t1=(TextView)findViewById(R.id.t1);
        tv2=(TextView)findViewById(R.id.tv2);
        wkt=(TextView)findViewById(R.id.wkt);
        tv12=(TextView)findViewById(R.id.tv12);
        tv1=(TextView)findViewById(R.id.tv1);
        card=(TextView)findViewById(R.id.card);
        t3=(TextView)findViewById(R.id.t3);
        cb1=(RadioButton) findViewById(R.id.cb1);
        cb2=(RadioButton) findViewById(R.id.cb2);
        btn=(TextView) findViewById(R.id.btn);
        score=(TextView)findViewById(R.id.score);
        zero=(Button)findViewById(R.id.zero);
        one=(Button)findViewById(R.id.one);
        two=(Button)findViewById(R.id.two);
        three=(Button)findViewById(R.id.three);
        four=(Button)findViewById(R.id.four);
        five=(Button)findViewById(R.id.five);
        six=(Button)findViewById(R.id.six);
        wide=(Button)findViewById(R.id.wide);
        noball=(Button)findViewById(R.id.noball);
        wicket=(Button)findViewById(R.id.wicket);
        summary=(TextView)findViewById(R.id.summary);

        mref= FirebaseDatabase.getInstance().getReference("Cricket");

        Intent intent=getIntent();
        ss=intent.getStringExtra("team1");
        s=intent.getStringExtra("team2");
        st=intent.getStringExtra("over");
        total=Integer.parseInt(st)*6;
        t1.setText(ss);
        t3.setText(s);
        tv2.setText("("+st+")");
        cb1.setText(ss);
        cb2.setText(s);

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(cb1.isChecked()==true&&cb2.isChecked()==false&&isCheck==false)
                {
                    isCheck=true;
                    score.setText("BATTING:"+ss);
                    alternate=s;
                    current=ss;
                    runs2=alternate+":0/0";
                }

            }
        });

        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cb2.isChecked()==true&&cb1.isChecked()==false&&isCheck==false)
                {
                    isCheck=true;
                    score.setText("BATTING:"+s);
                    alternate=ss;
                    current=s;
                    runs2=alternate+":0/0";
                }
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zero();
            }
        });

       one.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               one();

           }
       });

       two.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               two();
           }
       });

       three.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               three();
           }
       });

       four.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               four();
           }
       });

       five.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               five();
           }
       });

       six.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               six();

           }
       });

       wide.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               wide();

           }
       });

       noball.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               noball();

           }
       });
       wicket.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               wicket();
           }
       });

}

public void wicket()
{
    if(cb1.isChecked()==false&&cb2.isChecked()==false)
    {
        Toast t=Toast.makeText(getApplicationContext(),"Pleaase Choose The Team Batting First",Toast.LENGTH_LONG);
        t.show();
    }
    else if(balls<total&&wkts!=10) {
        if (flag != true)
            balls = balls + 1;
        else
            flag = false;
        div = balls / 6;
        rem = balls % 6;
        wkts = wkts + 1;
        tv1.setText(Integer.toString(div));
        tv12.setText(Integer.toString(rem));
        wkt.setText(Integer.toString(wkts));
    }
    if(balls==total||wkts==10)
    {
        if(inningsOver==0)
        {
            target=runs+1;
            summary.setText(Integer.toString(target));
            runs1 = Integer.toString(runs);
            wickets1 = Integer.toString(wkts);
            runs1 = current + ":" + runs1 + "/" + wickets1;
            wkts=0;
            runs=0;
            balls=0;
            div=0;
            rem=0;
            score.setText("BATTING:"+alternate);
            card.setText("0");
            wkt.setText("0");
            tv1.setText("0");
            tv12.setText("0");
        }
        if(inningsOver==1)
        {
            runs2 = Integer.toString(runs);
            wickets2=Integer.toString(wkts);
            runs2 = alternate + ":" + runs2 + "/" + wickets2;
            if(wkts==10&&runs<target)
                btn.setText("Team "+current+" WON");
        }
        inningsOver+=1;
        if(inningsOver==2)
        end(runs1,wickets1);
    }
    if(inningsOver==0) {
        runs1 = Integer.toString(runs);
        wickets1 = Integer.toString(wkts);
        runs1 = current + ":" + runs1 + "/" + wickets1;
    }
    else if(inningsOver==1)
    {
        runs2 = Integer.toString(runs);
        wickets2 = Integer.toString(wkts);
        runs2 = alternate + ":" + runs2 + "/" + wickets2;
    }
    Matches ob=new Matches(runs1,runs2);

    mref.child("vipss").setValue(ob);
}
    public void zero()
    {
        if(cb1.isChecked()==false&&cb2.isChecked()==false)
        {
            Toast t=Toast.makeText(getApplicationContext(),"Pleaase Choose The Team Batting First",Toast.LENGTH_LONG);
            t.show();
        }
        else if(balls<total&&wkts!=10){
            runs = runs + 0;
            if (flag != true)
                balls = balls + 1;
            else
                flag = false;
            div = balls / 6;
            rem = balls % 6;
            card.setText(Integer.toString(runs));
            tv1.setText(Integer.toString(div));
            tv12.setText(Integer.toString(rem));

        }
        if(balls==total||wkts==10||(runs>=target&&inningsOver!=0))
        {
            if(inningsOver==0)
            {
                target=runs+1;
                summary.setText(Integer.toString(target));
                runs1 = Integer.toString(runs);
                wickets1 = Integer.toString(wkts);
                runs1 = current + ":" + runs1 + "/" + wickets1;
                score.setText("BATTING:"+alternate);


                balls=0;
                div=0;
                rem=0;

                wkts=0;
                runs=0;
                card.setText("0");
                wkt.setText("0");
                tv1.setText("0");
                tv12.setText("0");
            }
            if(inningsOver==1)
            {
                runs2 = Integer.toString(runs);
                wickets2=Integer.toString(wkts);
                runs2 = alternate + ":" + runs2 + "/" + wickets2;
                if(runs>target)
                {
                    btn.setText("TEAM "+alternate+" WON");
                }
                else
                {
                    btn.setText("TEAM "+current+ " WON");
                }
            }
            inningsOver+=1;

            if(inningsOver==2)
                end(runs1,runs2);
        }
        if(inningsOver==0) {
            runs1 = Integer.toString(runs);
            wickets1 = Integer.toString(wkts);
            runs1 = current + ":" + runs1 + "/" + wickets1;
        }
        else if(inningsOver==1)
        {
            runs2 = Integer.toString(runs);
            wickets2 = Integer.toString(wkts);
            runs2 = alternate + ":" + runs2 + "/" + wickets2;
        }
        Matches ob=new Matches(runs1,runs2);

        mref.child("vipss").setValue(ob);
    }
public void one()
{
    if(cb1.isChecked()==false&&cb2.isChecked()==false)
    {
        Toast t=Toast.makeText(getApplicationContext(),"Pleaase Choose The Team Batting First",Toast.LENGTH_LONG);
        t.show();
    }
    else if(balls<total&&wkts!=10){
        runs = runs + 1;
        if (flag != true)
            balls = balls + 1;
        else
            flag = false;
        div = balls / 6;
        rem = balls % 6;
        card.setText(Integer.toString(runs));
        tv1.setText(Integer.toString(div));
        tv12.setText(Integer.toString(rem));

    }
    if(balls==total||wkts==10||(runs>=target&&inningsOver!=0))
    {
        if(inningsOver==0)
        {
            target=runs+1;
            summary.setText(Integer.toString(target));
            runs1 = Integer.toString(runs);
            wickets1 = Integer.toString(wkts);
            runs1 = current + ":" + runs1 + "/" + wickets1;
            score.setText("BATTING:"+alternate);


            balls=0;
            div=0;
            rem=0;

            wkts=0;
            runs=0;
            card.setText("0");
            wkt.setText("0");
            tv1.setText("0");
            tv12.setText("0");
        }
        if(inningsOver==1)
        {
            runs2 = Integer.toString(runs);
            wickets2=Integer.toString(wkts);
            runs2 = alternate + ":" + runs2 + "/" + wickets2;
            if(runs>target)
            {
                btn.setText("TEAM "+alternate+" WON");
            }
            else
            {
                btn.setText("TEAM "+current+ " WON");
            }
        }
        inningsOver+=1;

        if(inningsOver==2)
            end(runs1,runs2);
    }
    if(inningsOver==0) {
        runs1 = Integer.toString(runs);
        wickets1 = Integer.toString(wkts);
        runs1 = current + ":" + runs1 + "/" + wickets1;
    }
    else if(inningsOver==1)
    {
        runs2 = Integer.toString(runs);
        wickets2 = Integer.toString(wkts);
        runs2 = alternate + ":" + runs2 + "/" + wickets2;
    }
    Matches ob=new Matches(runs1,runs2);

    mref.child("vipss").setValue(ob);
}
public void two()
{
    if(cb1.isChecked()==false&&cb2.isChecked()==false)
    {
        Toast t=Toast.makeText(getApplicationContext(),"Pleaase Choose The Team Batting First",Toast.LENGTH_LONG);
        t.show();
    }
    else if(balls<total&&wkts!=10){
        runs = runs + 2;
        if (flag != true)
            balls = balls + 1;
        else
            flag = false;
        div = balls / 6;
        rem = balls % 6;
        card.setText(Integer.toString(runs));
        tv1.setText(Integer.toString(div));
        tv12.setText(Integer.toString(rem));
    }
    if(balls==total||wkts==10||(runs>=target&&inningsOver!=0))
    {
        if(inningsOver==0)
        {
            target=runs+1;
            summary.setText(Integer.toString(target));
            runs1 = Integer.toString(runs);
            wickets1 = Integer.toString(wkts);
            runs1 = current + ":" + runs1 + "/" + wickets1;
            score.setText("BATTING:"+alternate);


            balls=0;
            div=0;
            rem=0;

            wkts=0;
            runs=0;
            card.setText("0");
            wkt.setText("0");
            tv1.setText("0");
            tv12.setText("0");
        }
        if(inningsOver==1)
        {
            runs2 = Integer.toString(runs);
            wickets2=Integer.toString(wkts);
            runs2 = alternate + ":" + runs2 + "/" + wickets2;
            if(runs>target)
            {
                btn.setText("TEAM "+alternate+" WON");
            }
            else
            {
                btn.setText("TEAM "+current+" WON");
            }

        }
        inningsOver+=1;

        if(inningsOver==2)
            end(runs1,runs2);
    }
    if(inningsOver==0) {
        runs1 = Integer.toString(runs);
        wickets1 = Integer.toString(wkts);
        runs1 = current + ":" + runs1 + "/" + wickets1;
    }
    else if(inningsOver==1)
    {
        runs2 = Integer.toString(runs);
        wickets2 = Integer.toString(wkts);
        runs2 = alternate + ":" + runs2 + "/" + wickets2;
    }
    Matches ob=new Matches(runs1,runs2);

    mref.child("vipss").setValue(ob);
}
public void three()
{
    if(cb1.isChecked()==false&&cb2.isChecked()==false)
    {
        Toast t=Toast.makeText(getApplicationContext(),"Pleaase Choose The Team Batting First",Toast.LENGTH_LONG);
        t.show();
    }
    else if(balls<total&&wkts!=10){
        runs = runs + 3;
        if (flag != true)
            balls = balls + 1;
        else
            flag = false;
        div = balls / 6;
        rem = balls % 6;
        card.setText(Integer.toString(runs));
        tv1.setText(Integer.toString(div));
        tv12.setText(Integer.toString(rem));
    }
    if(balls==total||wkts==10||(runs>=target&&inningsOver!=0))
    {
        if(inningsOver==0)
        {
            target=runs+1;
            summary.setText(Integer.toString(target));
            runs1 = Integer.toString(runs);
            wickets1 = Integer.toString(wkts);
            runs1 = current + ":" + runs1 + "/" + wickets1;
            score.setText("BATTING:"+alternate);


            balls=0;
            div=0;
            rem=0;

            wkts=0;
            runs=0;
            card.setText("0");
            wkt.setText("0");
            tv1.setText("0");
            tv12.setText("0");
        }
        if(inningsOver==1)
        {
            runs2 = Integer.toString(runs);
            wickets2=Integer.toString(wkts);
            runs2 = alternate + ":" + runs2 + "/" + wickets2;
            if(runs>target)
            {
                btn.setText("TEAM "+alternate+" WON");
            }
            else
            {
                btn.setText("TEAM "+current+" WON");
            }
        }
        inningsOver+=1;

        if(inningsOver==2)
            end(runs1,runs2);
    }
    if(inningsOver==0) {
        runs1 = Integer.toString(runs);
        wickets1 = Integer.toString(wkts);
        runs1 = current + ":" + runs1 + "/" + wickets1;
    }
    else if(inningsOver==1)
    {
        runs2 = Integer.toString(runs);
        wickets2 = Integer.toString(wkts);
        runs2 = alternate + ":" + runs2 + "/" + wickets2;
    }
    Matches ob=new Matches(runs1,runs2);

    mref.child("vipss").setValue(ob);
}
public void four()
{
    if(cb1.isChecked()==false&&cb2.isChecked()==false)
    {
        Toast t=Toast.makeText(getApplicationContext(),"Pleaase Choose The Team Batting First",Toast.LENGTH_LONG);
        t.show();
    }
    else if(balls<total&&wkts!=10){
        runs = runs + 4;
        if (flag != true)
            balls = balls + 1;
        else
            flag = false;
        div = balls / 6;
        rem = balls % 6;
        card.setText(Integer.toString(runs));
        tv1.setText(Integer.toString(div));
        tv12.setText(Integer.toString(rem));
    }
    if(balls==total||wkts==10||(runs>=target&&inningsOver!=0))
    {
        if(inningsOver==0)
        {
            target=runs+1;
            summary.setText(Integer.toString(target));
            runs1 = Integer.toString(runs);
            wickets1 = Integer.toString(wkts);
            runs1 = current + ":" + runs1 + "/" + wickets1;
            score.setText("BATTING:"+alternate);


            wkts=0;
            balls=0;
            div=0;
            rem=0;

            runs=0;
            card.setText("0");
            wkt.setText("0");
            tv1.setText("0");
            tv12.setText("0");

        }
        if(inningsOver==1)
        {
            runs2 = Integer.toString(runs);
            wickets2=Integer.toString(wkts);
            runs2 = alternate + ":" + runs2 + "/" + wickets2;
            if(runs>target)
            {
                btn.setText("TEAM "+alternate+" WON");
            }
            else
            {
                btn.setText("TEAM "+current+" WON");
            }
        }
        inningsOver+=1;

        if(inningsOver==2)
            end(runs1,runs2);
    }
    if(inningsOver==0) {
        runs1 = Integer.toString(runs);
        wickets1 = Integer.toString(wkts);
        runs1 = current + ":" + runs1 + "/" + wickets1;
    }
    else if(inningsOver==1)
    {
        runs2 = Integer.toString(runs);
        wickets2 = Integer.toString(wkts);
        runs2 = alternate + ":" + runs2 + "/" + wickets2;
    }
    Matches ob=new Matches(runs1,runs2);

    mref.child("vipss").setValue(ob);
}
public void five()
{
    if(cb1.isChecked()==false&&cb2.isChecked()==false)
    {
        Toast t=Toast.makeText(getApplicationContext(),"Pleaase Choose The Team Batting First",Toast.LENGTH_LONG);
        t.show();
    }
    else if(balls<total&&wkts!=10){
        runs = runs + 5;
        if (flag != true)
            balls = balls + 1;
        else
            flag = false;
        div = balls / 6;
        rem = balls % 6;
        card.setText(Integer.toString(runs));
        tv1.setText(Integer.toString(div));
        tv12.setText(Integer.toString(rem));
    }
    if(balls==total||wkts==10||(runs>=target&&inningsOver!=0))
    {
        if(inningsOver==0)
        {
            target=runs+1;
            summary.setText(Integer.toString(target));
            runs1 = Integer.toString(runs);
            wickets1 = Integer.toString(wkts);
            runs1 = current + ":" + runs1 + "/" + wickets1;
            score.setText("BATTING:"+alternate);


            balls=0;
            div=0;
            rem=0;

            wkts=0;
            runs=0;
            card.setText("0");
            wkt.setText("0");
            tv1.setText("0");
            tv12.setText("0");
        }
        if(inningsOver==1)
        {
            runs2 = Integer.toString(runs);
            wickets2=Integer.toString(wkts);
            runs2 = alternate + ":" + runs2 + "/" + wickets2;
            if(runs>target)
            {
                btn.setText("TEAM "+alternate+" WON");
            }
            else
            {
                btn.setText("TEAM "+current+" WON");
            }
        }

        inningsOver+=1;
        if(inningsOver==2)
            end(runs1,runs2);
    }
    if(inningsOver==0) {
        runs1 = Integer.toString(runs);
        wickets1 = Integer.toString(wkts);
        runs1 = current + ":" + runs1 + "/" + wickets1;
    }
    else if(inningsOver==1)
    {
        runs2 = Integer.toString(runs);
        wickets2 = Integer.toString(wkts);
        runs2 = alternate + ":" + runs2 + "/" + wickets2;
    }
    Matches ob=new Matches(runs1,runs2);

    mref.child("vipss").setValue(ob);
}
public void six()
{
    if(cb1.isChecked()==false&&cb2.isChecked()==false)
    {
        Toast t=Toast.makeText(getApplicationContext(),"Pleaase Choose The Team Batting First",Toast.LENGTH_LONG);
        t.show();
    }
    else if(balls<total&&wkts!=10){
        runs = runs + 6;
        if (flag != true)
            balls = balls + 1;
        else
            flag = false;
        div = balls / 6;
        rem = balls % 6;
        card.setText(Integer.toString(runs));
        tv1.setText(Integer.toString(div));
        tv12.setText(Integer.toString(rem));
    }
    if(balls==total||wkts==10||(runs>=target&&inningsOver!=0))
    {
        if(inningsOver==0)
        {
            target=runs+1;
            summary.setText(Integer.toString(target));
            runs1 = Integer.toString(runs);
            wickets1 = Integer.toString(wkts);
            runs1 = current + ":" + runs1 + "/" + wickets1;
            score.setText("BATTING:"+alternate);


            balls=0;
            div=0;
            rem=0;

            wkts=0;
            runs=0;
            card.setText("0");
            wkt.setText("0");
            tv1.setText("0");
            tv12.setText("0");
        }
        if(inningsOver==1)
        {
            runs2 = Integer.toString(runs);
            wickets2=Integer.toString(wkts);
            runs2 = alternate + ":" + runs2 + "/" + wickets2;
            if(runs>target)
            {
                btn.setText("TEAM "+alternate+" WON");
            }
            else
            {
                btn.setText("TEAM "+current+" WON");
            }
        }
        inningsOver+=1;

        if(inningsOver==2)
            end(runs1,runs2);
    }
    if(inningsOver==0) {
        runs1 = Integer.toString(runs);
        wickets1 = Integer.toString(wkts);
        runs1 = current + ":" + runs1 + "/" + wickets1;
    }
    else if(inningsOver==1)
    {
        runs2 = Integer.toString(runs);
        wickets2 = Integer.toString(wkts);
        runs2 = alternate + ":" + runs2 + "/" + wickets2;
    }
    Matches ob=new Matches(runs1,runs2);

    mref.child("vipss").setValue(ob);
}
public void wide()
{
    if(cb1.isChecked()==false&&cb2.isChecked()==false)
    {
        Toast t=Toast.makeText(getApplicationContext(),"Pleaase Choose The Team Batting First",Toast.LENGTH_LONG);
        t.show();
    }
    else {
        runs = runs + 1;
        card.setText(Integer.toString(runs));
    }
    if(runs>=target&&inningsOver!=0)
    {

            btn.setText("TEAM "+alternate+" WON");

        runs2 = Integer.toString(runs);
        wickets2=Integer.toString(wkts);
        runs2 = alternate + ":" + runs2 + "/" + wickets2;
        end(runs1,runs2);
    }
    if(inningsOver==0) {
        runs1 = Integer.toString(runs);
        wickets1 = Integer.toString(wkts);
        runs1 = current + ":" + runs1 + "/" + wickets1;
    }
    else if(inningsOver==1)
    {
        runs2 = Integer.toString(runs);
        wickets2 = Integer.toString(wkts);
        runs2 = alternate + ":" + runs2 + "/" + wickets2;
    }
    Matches ob=new Matches(runs1,runs2);

    mref.child("vipss").setValue(ob);
}
public void noball()
{

    if(cb1.isChecked()==false&&cb2.isChecked()==false)
    {
        Toast t=Toast.makeText(getApplicationContext(),"Pleaase Choose The Team Batting First",Toast.LENGTH_LONG);
        t.show();
    }
    else {
        runs = runs + 1;
        Toast t1 = Toast.makeText(getApplicationContext(), "Now Choose The Run Scored On Noball", Toast.LENGTH_LONG);
        flag = true;
        card.setText(Integer.toString(runs));
    }
    if(runs>=target&&inningsOver!=0)
    {
        btn.setText("TEAM "+alternate+" WON");
        runs2 = Integer.toString(runs);
        wickets2=Integer.toString(wkts);
        runs2 = alternate + ":" + runs2 + "/" + wickets2;
        end(runs1,runs2);
    }
    if(inningsOver==0) {
        runs1 = Integer.toString(runs);
        wickets1 = Integer.toString(wkts);
        runs1 = current + ":" + runs1 + "/" + wickets1;
    }
    else if(inningsOver==1)
    {
        runs2 = Integer.toString(runs);
        wickets2 = Integer.toString(wkts);
        runs2 = alternate + ":" + runs2 + "/" + wickets2;
    }
    Matches ob=new Matches(runs1,runs2);

    mref.child("vipss").setValue(ob);
}
public void end(String scores,String fow)
{
    Matches ob=new Matches(scores,fow);

        mref.child("vipss").setValue(ob);
}
}
