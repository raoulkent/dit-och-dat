package com.example.simplegolf;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.testcourses.TestCourses;
import com.example.simplegolf.ui.courseselect.CourseListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseSelectActivity extends AppCompatActivity {
    List<Course> courses = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private CourseListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);

        this.setTitle(R.string.select_course);



        // TODO: Replace dummy data with real data.
        //createExampleCourseList();

        //buildRecyclerView();
        loadCourses();
    }

    private void loadCourses() {
        Repository repository = Repository.getRepository(this);
        Log.d("COURSES", "ATTEMPTING TO FETCH REMOTE ");

        repository.getAllCoursesFromRemote(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                Log.d("COURSES", "Success, showing remote courses");
                courses = response.body();

                runOnUiThread(() -> {
                    buildRecyclerView();
                });
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Log.d("COURSES", "Failure, load local courses ");

                runOnUiThread(() -> {
                    createExampleCourseList();
                    buildRecyclerView();
                });
            }
        });
    }

    private void buildRecyclerView() {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView textLoading = findViewById(R.id.textLoading);
        progressBar.setVisibility(View.INVISIBLE);
        textLoading.setVisibility(View.INVISIBLE);
        mRecyclerView = findViewById(R.id.course_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CourseListAdapter(courses, this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void createExampleCourseList() {
        courses.add(TestCourses.INSTANCE.getCourseChalmers());
        courses.add(TestCourses.INSTANCE.getCourseAlingsas());
        courses.add(TestCourses.INSTANCE.getCourseOijared());
    }
}