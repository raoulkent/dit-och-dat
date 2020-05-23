package com.example.simplegolf.ui.courseselect;

import androidx.lifecycle.ViewModel;

import com.example.simplegolf.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseSelectViewModel extends ViewModel {

    private List<Course> courses;

    public CourseSelectViewModel(){
        courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }
}