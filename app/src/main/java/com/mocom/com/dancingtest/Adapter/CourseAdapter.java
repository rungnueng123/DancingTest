package com.mocom.com.dancingtest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mocom.com.dancingtest.Holder.CourseHolder;
import com.mocom.com.dancingtest.Model.RecyclerViewClickListener;
import com.mocom.com.dancingtest.Model.dao.CourseDao;
import com.mocom.com.dancingtest.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseHolder> {

    private RecyclerViewClickListener listener;
    private List<CourseDao> courses;
    private Context context;

    public CourseAdapter(List<CourseDao> courses, Context context, RecyclerViewClickListener listener) {
        this.courses = courses;
        this.context = context;
        this.listener = listener;
    }


    public void updateData(List<CourseDao> dataset) {
        courses.clear();
        courses.addAll(dataset);
        notifyDataSetChanged();

    }

    public void setCourses(List<CourseDao> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_menu, parent, false);
        return new CourseHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        CourseDao item = courses.get(position);
        holder.getCourseName().setText(item.getCourseName());
        holder.getDate().setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        if (courses == null) {
            return 0;
        }
        if (courses.size() == 0) {
            return 0;
        }
        return courses.size();
    }
}
