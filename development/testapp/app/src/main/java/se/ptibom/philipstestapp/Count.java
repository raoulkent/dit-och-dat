package se.ptibom.philipstestapp;

import java.io.Serializable;

public class Count implements Serializable {
    private String randomText;
    private int counter;

    public Count(String randomText) {
        this.randomText = randomText;
        this.counter = 0;
    }

    public void countUp() {
        counter++;
    }

    public String getRandomText() {
        return randomText;
    }

    public String getCounter() {
        return counter + ""; // Convert int to String.
    }
}
