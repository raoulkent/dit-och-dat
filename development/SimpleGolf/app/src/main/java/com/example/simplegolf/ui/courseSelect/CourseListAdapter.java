package com.example.simplegolf.ui.courseSelect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.GameActivity;
import com.example.simplegolf.R;
import com.example.simplegolf.model.Course;

import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder> {
    private List<Course> courses;
    private Context context;

    public CourseListAdapter(List<Course> courses, Context context) {
        this.courses = courses;
        this.context = context;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class CourseViewHolder extends RecyclerView.ViewHolder{
        TextView courseName;
        ImageView thumbnail;

        CourseViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.course_thumbnail);
            courseName = itemView.findViewById(R.id.course_name);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    @NonNull
    public CourseListAdapter.CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card, parent, false);
        return new CourseViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(final CourseViewHolder holder, int position) {
        // - get element from your data set at this position
        // - replace the contents of the view with that element
        holder.courseName.setText(courses.get(position).getName());
        holder.thumbnail.setImageResource(R.drawable.chgk_logo); // TODO: Replace with dynamic logo loading
        holder.thumbnail.setPadding(2, 2, 2, 2);

        holder.itemView.setOnClickListener(v -> selectCourse(holder));
    }

    private void selectCourse(CourseViewHolder holder) {
        Course course = courses.get(holder.getAdapterPosition());
        Intent goGameActivity = new Intent(context.getApplicationContext(), GameActivity.class);
        goGameActivity.putExtra("course", course);
        context.startActivity(goGameActivity);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}