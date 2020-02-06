package com.example.scorecard;

public class Matches {
    String team1,team2;

    public Matches() {
    }

    public Matches(String team1,String team2) {

        this.team1 = team1;
        this.team2 = team2;
    }


    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }
}
