package com.example.simplegolf.database;

import android.content.Context;

import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.database.AppDatabase;
import com.example.simplegolf.model.database.ScorecardDAO;
import com.example.simplegolf.model.testcourses.TestCourses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ScorecardDAOTest {
    private ScorecardDAO scorecardDAO;
    private AppDatabase db;
    private Scorecard scorecard;

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        scorecardDAO = db.scorecardDAO();
        scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testInsert() {
        long id = scorecardDAO.insert(scorecard);
        assertEquals(1, id);
        id = scorecardDAO.insert(scorecard);
        assertEquals(2, id);
    }

    @Test
    public void testGetUnfinishedRounds() {
        scorecardDAO.insert(scorecard);
        List<Scorecard> unfinishedRounds = scorecardDAO.getUnfinishedRounds();
        assertEquals(1, unfinishedRounds.size());
        assertFalse(unfinishedRounds.get(0).isFinishedRound());
    }

    @Test
    public void testGetFinishedRounds() {
        scorecard.setFinishedRound(true);
        scorecardDAO.insert(scorecard);
        List<Scorecard> finishedRounds = scorecardDAO.getFinishedRounds();
        assertEquals(1, finishedRounds.size());
        assertTrue(finishedRounds.get(0).isFinishedRound());
    }

    @Test
    public void testUpdate() {
        scorecardDAO.insert(scorecard); // Insert card
        List<Scorecard> unfinishedRounds = scorecardDAO.getUnfinishedRounds();
        assertEquals(1, unfinishedRounds.size()); // Check that DB size is correct
        assertFalse(unfinishedRounds.get(0).isFinishedRound());

        // Update a scorecard value
        scorecard = unfinishedRounds.get(0);
        scorecard.setFinishedRound(true);
        scorecardDAO.update(scorecard);

        // Unfinished should now be 0 and finished should be 1.
        unfinishedRounds = scorecardDAO.getUnfinishedRounds();
        assertEquals(0, unfinishedRounds.size());

        List<Scorecard> finishedRounds = scorecardDAO.getFinishedRounds();
        assertEquals(1, finishedRounds.size());
        assertTrue(finishedRounds.get(0).isFinishedRound());
    }

    @Test
    public void testDelete() {
        scorecardDAO.insert(scorecard);
        List<Scorecard> scorecards = scorecardDAO.getUnfinishedRounds();
        assertEquals(1, scorecards.size());

        scorecardDAO.delete(scorecards.get(0));
        scorecards = scorecardDAO.getUnfinishedRounds();
        assertEquals(0, scorecards.size());
    }

    @Test
    public void testDeleteAll() {
        scorecardDAO.insert(scorecard);
        scorecardDAO.insert(scorecard);
        List<Scorecard> scorecards = scorecardDAO.getUnfinishedRounds();
        assertEquals(2, scorecards.size());

        scorecardDAO.deleteAll();
        scorecards = scorecardDAO.getUnfinishedRounds();
        assertEquals(0, scorecards.size());
    }

    @Test
    public void testGetById() {
        String expected1 = "111";
        String expected2 = "123";
        scorecard.setDate(expected1);
        long id1 = scorecardDAO.insert(scorecard);
        scorecard.setDate(expected2);
        long id2 = scorecardDAO.insert(scorecard);
        Scorecard fetchedCard1 = scorecardDAO.getById(id1);
        Scorecard fetchedCard2 = scorecardDAO.getById(id2);

        assertEquals(expected1, fetchedCard1.getDate());
        assertEquals(expected2, fetchedCard2.getDate());
    }

    @Test
    public void testDeleteAllUnfinished() {
        scorecardDAO.insert(scorecard);
        scorecardDAO.insert(scorecard);
        scorecard.setFinishedRound(true);
        scorecardDAO.insert(scorecard);
        scorecardDAO.insert(scorecard);
        List<Scorecard> scorecards = scorecardDAO.getUnfinishedRounds();
        assertEquals(2, scorecards.size());
        scorecards = scorecardDAO.getFinishedRounds();
        assertEquals(2, scorecards.size());

        scorecardDAO.deleteAllUnfinished();
        scorecards = scorecardDAO.getUnfinishedRounds();
        assertEquals(0, scorecards.size());
        scorecards = scorecardDAO.getFinishedRounds();
        assertEquals(2, scorecards.size());
    }


    @Test
    public void testDeleteAllFinished() {
        scorecardDAO.insert(scorecard);
        scorecardDAO.insert(scorecard);
        scorecard.setFinishedRound(true);
        scorecardDAO.insert(scorecard);
        scorecardDAO.insert(scorecard);
        List<Scorecard> scorecards = scorecardDAO.getUnfinishedRounds();
        assertEquals(2, scorecards.size());
        scorecards = scorecardDAO.getFinishedRounds();
        assertEquals(2, scorecards.size());

        scorecardDAO.deleteAllFinished();
        scorecards = scorecardDAO.getUnfinishedRounds();
        assertEquals(2, scorecards.size());
        scorecards = scorecardDAO.getFinishedRounds();
        assertEquals(0, scorecards.size());
    }
}
