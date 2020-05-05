package com.example.simplegolf.model.testGames;

import android.util.Log;

import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.testcourses.TestCourses;

/**
 * Singleton that contains hardcoded test Games.
 * Use get methods to get a constructed Scorecard.
 */
public enum TestGame {
    INSTANCE;
    private Scorecard oldGame;

    /**
     * Generate Scorecard
     */
    TestGame() {
        try {
            oldGame = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }

        oldGame.setDate("2020-04-28");

        oldGame.addPlayer("P1", "1", TestCourses.INSTANCE.getCourseChalmers().getTees().get(0), 36.0);
        oldGame.addPlayer("P2", "2", TestCourses.INSTANCE.getCourseChalmers().getTees().get(1), 36.0);
        oldGame.addPlayer("P3", "3", TestCourses.INSTANCE.getCourseChalmers().getTees().get(2), 36.0);
        oldGame.addPlayer("P4", "4", TestCourses.INSTANCE.getCourseChalmers().getTees().get(3), 36.0);

        for (Player p : oldGame.getPlayers()) {
            for (int i = 0; i < p.getShots().length; i++) {
                p.setShotsForHole(i, (int) (Math.random() * 10 + 1));
            }
        }
    }

    public Scorecard getGame() {
        return oldGame;
    }
}
