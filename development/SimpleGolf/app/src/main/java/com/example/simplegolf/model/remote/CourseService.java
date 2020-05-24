package com.example.simplegolf.model.remote;

import com.example.simplegolf.model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CourseService {
    @GET("course")
    Call<List<Course>> courses();
}
