package com.example.simplegolf.ui.courseselect;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.PlayerSelectActivity;
import com.example.simplegolf.R;
import com.example.simplegolf.model.Course;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder> {
    private CourseSelectViewModel viewModel;
    private Activity activity;

    public CourseListAdapter(CourseSelectViewModel viewModel, Activity activity) {
        this.viewModel = viewModel;
        this.activity = activity;
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView courseName;
        ImageView thumbnail;

        CourseViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.course_thumbnail);
            courseName = itemView.findViewById(R.id.course_name);
        }
    }

    @Override
    @NonNull
    public CourseListAdapter.CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CourseViewHolder holder, int position) {
        holder.courseName.setText(viewModel.getCourses().get(position).getName());
        holder.thumbnail.setImageResource(R.drawable.chgk_logo); // TODO: Replace with dynamic logo loading
        holder.thumbnail.setPadding(2, 2, 2, 2);

        holder.itemView.setOnClickListener(v -> selectCourse(holder));
    }

    private void selectCourse(CourseViewHolder holder) {
        Course course = viewModel.getCourses().get(holder.getAdapterPosition());
        Intent goGameActivity = new Intent(activity.getApplicationContext(), PlayerSelectActivity.class);
        goGameActivity.putExtra("course", course);
        activity.finish();
        activity.startActivity(goGameActivity);
    }

    @Override
    public int getItemCount() {
        return viewModel.getCourses().size();
    }
}