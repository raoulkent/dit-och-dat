package model;

import java.io.Serializable;

/**
 * A Tee is chosen by a golf player before starting each round.
 * It is used to calculate handicap for different difficulties
 */
public class Tee implements Serializable {
    private String name;
    private double courseRating;
    private double slopeRating;

    /**
     * Tee constructor
     *
     * @param name         The tee name i.e  "Red - Male"
     * @param courseRating The course rating for the tee
     * @param slopeRating  The slope rating for the tee
     */
    public Tee(String name, double courseRating, double slopeRating) {
        this.name = name;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(double courseRating) {
        this.courseRating = courseRating;
    }

    public double getSlopeRating() {
        return slopeRating;
    }

    public void setSlopeRating(double slopeRating) {
        this.slopeRating = slopeRating;
    }


}