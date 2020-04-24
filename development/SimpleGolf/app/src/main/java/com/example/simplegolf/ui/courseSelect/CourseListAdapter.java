package com.example.simplegolf.ui.courseSelect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Course;

import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder> {
    private List<Course> courses;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView courseName;
        public ImageView thumbnail;

        CourseViewHolder(View v) {
            super(v);
//            textView = v;
        }
    }

    // TODO: Give proper data to the Adapter
    // Provide a suitable constructor (depends on the kind of dataset)
    public CourseListAdapter(List<Course> courses) {
        this.courses = courses;
    }


    // Create new views (invoked by the layout manager)
    @Override @NonNull
    public CourseListAdapter.CourseViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_card, parent, false);

        return new CourseViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final CourseViewHolder holder, int position) {
        // - get element from your data set at this position
        // - replace the contents of the view with that element
        holder.courseName.setText(courses.get(position).getName());

        // TODO: Replace all relevant fields in the holder with actual data.

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return courses.size();
    }

}
