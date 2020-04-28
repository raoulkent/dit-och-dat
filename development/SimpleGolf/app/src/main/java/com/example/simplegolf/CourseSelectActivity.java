package com.example.simplegolf;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Hole;
import com.example.simplegolf.model.Tee;
import com.example.simplegolf.model.testcourses.TestCourses;
import com.example.simplegolf.ui.courseSelect.CourseListAdapter;

import java.util.ArrayList;
import java.util.Random;

public class CourseSelectActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);
        recyclerView = findViewById(R.id.course_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
//        try {
//            mAdapter = new CourseListAdapter(courseArrBuilder());   // TODO: Replace this dummy set of courses with complete course loading
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        recyclerView.setAdapter(mAdapter);

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(TestCourses.INSTANCE.getCourseChalmers());

        CourseListAdapter courseAdapter = new CourseListAdapter(courses);

        recyclerView.setAdapter(courseAdapter);
    }

    private ArrayList<Course> courseArrBuilder() throws Exception {
        ArrayList<Tee> tees1, tees2;
        tees1 = new ArrayList<>();
        tees1.add(new Tee("Blue", 97.0, 123.0));
        tees1.add(new Tee("White", 117.3, 133.4));

        tees2 = new ArrayList<>();
        tees2.add(new Tee("Blue", 87.0, 113.0));
        tees2.add(new Tee("White", 107.3, 123.4));

        Course course1 = new Course("First course", holeBuilder(3), tees1);
        Course course2 = new Course("Second course", holeBuilder(9), tees2);

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);

        return courses;
    }

    private ArrayList<Hole> holeBuilder(int numberOfHoles) {
        ArrayList<Hole> holes = new ArrayList<>();
        for (int i = 0; i <= numberOfHoles; i++) {
            holes.add(new Hole(new Random().nextInt(10), new Random().nextInt(10)));
        }
        return holes;
    }
}