package com.example.simplegolf.ui.gameHistory;

import android.content.Context;

import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.Scorecard;

import java.util.ArrayList;
import java.util.List;

public class GameHistoryViewModel {
    private List<Scorecard> scorecards;
    private boolean completedGames;
    private Repository repository;
    private Context context;

    public GameHistoryViewModel(Context context){
        this.context = context;
        scorecards = new ArrayList<>();
        repository = Repository.getRepository(context);
    }

    public List<Scorecard> getScorecards() {
        return scorecards;
    }

    public void setScorecards(List<Scorecard> scorecards) {
        this.scorecards = scorecards;
    }

    public void removeScorecard(Scorecard scorecard) {
        deleteGame(scorecard);
        scorecards.remove(scorecard);
    }

    public void loadGames() {
        new Thread(() -> {
            if (!completedGames) {
                setScorecards(repository.getDb().scorecardDAO().getUnfinishedRounds());
            } else {
                setScorecards(repository.getDb().scorecardDAO().getFinishedRounds());
            }
        }).start();

        // TODO: runOnUiThread?
    }

    private void deleteGame(Scorecard scorecard) {
        new Thread(() -> {
            if (getScorecards().contains(scorecard)) {
                repository.getDb().scorecardDAO().delete(scorecard);
            }
        }).start();

        // TODO: runOnUiThread?
    }

    public void setCompletedGames(boolean completedGames) {
        this.completedGames = completedGames;
    }
}
