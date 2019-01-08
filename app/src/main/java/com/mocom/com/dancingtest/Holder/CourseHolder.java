package com.mocom.com.dancingtest.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mocom.com.dancingtest.Model.RecyclerViewClickListener;
import com.mocom.com.dancingtest.R;

public class CourseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView courseName;
    private TextView date;
    private RecyclerViewClickListener mListener;

    public TextView getCourseName() {
        return courseName;
    }

    public TextView getDate() {
        return date;
    }

    public CourseHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
        super(itemView);
        mListener = listener;
        courseName = itemView.findViewById(R.id.txt_course_name);
        date = itemView.findViewById(R.id.txt_date);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(),courseName.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        mListener.onClick(v, getAdapterPosition());
    }
}
