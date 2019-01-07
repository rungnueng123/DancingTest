package com.mocom.com.dancingtest.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mocom.com.dancingtest.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CourseFragment extends Fragment implements View.OnClickListener {

    EditText edtCourse;
    Button btnAdd;
    DatePicker datePicker;
    Calendar calendar;
    InputStream is = null;
    String selectDate = "";
    String addUrl = "http://10.10.30.92/KotlinCalendarPHP/AddCourse.php";

    public CourseFragment() {
        super();
    }

    public static CourseFragment newInstance() {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstances(View rootView, Bundle savedInstanceState) {

        calendar = Calendar.getInstance();
        edtCourse = rootView.findViewById(R.id.edt_course);
        btnAdd = rootView.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        datePicker = rootView.findViewById(R.id.date_picker);

    }

    private void init(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            String year = "" + datePicker.getYear();
            String month = "" + (datePicker.getMonth() + 1);
            String day = "" + datePicker.getDayOfMonth();
            if ((datePicker.getMonth() + 1) < 10) {
                month = "0" + month;
            }
            if (datePicker.getDayOfMonth() < 10) {

                day = "0" + day;
            }
            selectDate = year + "-" + month + "-" + day;
//            Toast.makeText(getActivity(), selectDate + " " + edtCourse.getText().toString(), Toast.LENGTH_LONG).show();
            addCourse(edtCourse.getText().toString(), selectDate);
        }
    }

    private void addCourse(final String course, final String date) {
        if (!course.isEmpty()) {
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            StringRequest request = new StringRequest(Request.Method.POST, addUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
//                    Log.d("onResponse",response);
//                    edtCourse.setText("");
//                    Toast.makeText(getActivity(),"เพิ่มข้อมูลแล้วจ้า",Toast.LENGTH_SHORT).show();
                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(response);

                        Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    Log.d("onError", error.toString());
//                    Toast.makeText(getActivity(), "เกิดข้อผิดพลาดโปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("CourseName", course);
                    params.put("Date", date);

                    return params;
                }
            };
            requestQueue.add(request);

        }
//        String year = "" + datePicker.getYear();
//        String month = "" + (datePicker.getMonth() + 1);
//        String day = "" + datePicker.getDayOfMonth();
//        if ((datePicker.getMonth() + 1) < 10) {
//            month = "0" + month;
//        }
//        if (datePicker.getDayOfMonth() < 10) {
//
//            day = "0" + day;
//        }
//        selectDate = year + "-" + month + "-" + day;
//        Toast.makeText(getActivity(), selectDate+" "+edtCourse.getText().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }
}
