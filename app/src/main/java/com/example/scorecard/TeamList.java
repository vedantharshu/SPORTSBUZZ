package com.example.scorecard;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TeamList extends ArrayAdapter<Matches> {

    private Activity context;
    private List<Matches> matchesList;

    public TeamList(Activity context, List<Matches> matchesList)
    {
        super(context,R.layout.cricuser,matchesList);
        this.context=context;
        this.matchesList=matchesList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();
        View listViewitem=inflater.inflate(R.layout.cricuser,null,true);
        TextView team1=(TextView)listViewitem.findViewById(R.id.team1);
        TextView team2=(TextView)listViewitem.findViewById(R.id.team2);

        Matches ob= matchesList.get(position);

        team1.setText(ob.getTeam1());
        team2.setText(ob.getTeam2());

        return listViewitem;
    }
}
