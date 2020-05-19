package com.example.simplegolf.ui.courseselect;

import androidx.lifecycle.ViewModel;

import com.example.simplegolf.model.Course;

import java.util.List;

public class CourseSelectViewModel extends ViewModel {

    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}