package com.mocom.com.dancingtest.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mocom.com.dancingtest.R;

public class CourseHolder extends RecyclerView.ViewHolder {

    private TextView courseName;
    private TextView date;

    public TextView getCourseName() {
        return courseName;
    }

    public TextView getDate() {
        return date;
    }

    public CourseHolder(@NonNull View itemView) {
        super(itemView);
        courseName = itemView.findViewById(R.id.txt_course_name);
        date = itemView.findViewById(R.id.txt_date);
    }
}
