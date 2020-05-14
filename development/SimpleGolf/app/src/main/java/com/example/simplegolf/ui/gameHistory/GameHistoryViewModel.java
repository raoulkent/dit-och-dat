package com.example.simplegolf.ui.gameHistory;

import com.example.simplegolf.model.Scorecard;

import java.util.ArrayList;
import java.util.List;

public class GameHistoryViewModel {
    private List<Scorecard> scorecards;

    public GameHistoryViewModel(){
        scorecards = new ArrayList<>();
    }

    public List<Scorecard> getScorecards() {
        return scorecards;
    }

    public void setScorecards(List<Scorecard> scorecards) {
        this.scorecards = scorecards;
    }

    public void removeScorecard(Scorecard scorecard) {
        scorecards.remove(scorecard);
    }
}
