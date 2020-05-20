package com.example.simplegolf.ui.gamehistory;

import android.app.Activity;

import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.Scorecard;

import java.util.ArrayList;
import java.util.List;

public class GameHistoryViewModel {
    private List<Scorecard> scorecards;
    private boolean completedGames;
    private Repository repository;
    private Activity activity;

    public GameHistoryViewModel(Activity activity) {
        scorecards = new ArrayList<>();
        repository = Repository.getRepository(activity);
        this.activity = activity;
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

    public void loadGames(GameHistoryAdapter adapter) { // todo if there is time, remake to an observer pattern.
        new Thread(() -> {
            List<Scorecard> list;
            if (!completedGames) {
                list = repository.getDb().scorecardDAO().getUnfinishedRounds();
            } else {
                list = repository.getDb().scorecardDAO().getFinishedRounds();
            }
            activity.runOnUiThread(() -> {
                setScorecards(list);
                adapter.notifyDataSetChanged();
            });
        }).start();
    }

    private void deleteGame(Scorecard scorecard) {
        new Thread(() -> repository.getDb().scorecardDAO().delete(scorecard)).start();
    }

    public void setCompletedGames(boolean completedGames) {
        this.completedGames = completedGames;
    }
}
