package com.example.simplegolf;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.testcourses.TestCourses;
import com.example.simplegolf.ui.courseselect.CourseListAdapter;

import java.util.ArrayList;

public class CourseSelectActivity extends AppCompatActivity {
    ArrayList<Course> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);

        this.setTitle("Select a course");


        // TODO: Replace dummy data with real data.
        createExampleCourseList();

        buildRecyclerView();
    }

    private void buildRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.course_recycler);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        CourseListAdapter mAdapter = new CourseListAdapter(courses, this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void createExampleCourseList() {
        courses.add(TestCourses.INSTANCE.getCourseChalmers());
        courses.add(TestCourses.INSTANCE.getCourseChalmers());
        courses.add(TestCourses.INSTANCE.getCourseChalmers());
        courses.add(TestCourses.INSTANCE.getCourseChalmers());
        courses.add(TestCourses.INSTANCE.getCourseChalmers());
        courses.add(TestCourses.INSTANCE.getCourseChalmers());
        courses.add(TestCourses.INSTANCE.getCourseChalmers());
        courses.add(TestCourses.INSTANCE.getCourseChalmers());
        courses.add(TestCourses.INSTANCE.getCourseChalmers());
    }
}