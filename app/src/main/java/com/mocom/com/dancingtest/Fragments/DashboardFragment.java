package com.mocom.com.dancingtest.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mocom.com.dancingtest.Adapter.CourseAdapter;
import com.mocom.com.dancingtest.Model.RecyclerViewClickListener;
import com.mocom.com.dancingtest.Model.dao.CourseDao;
import com.mocom.com.dancingtest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mocom.com.dancingtest.Config.Config.DATA_URL;

public class DashboardFragment extends Fragment {

    private String jsonUrl = DATA_URL + "json_get_course.php";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CourseDao> courseList;

    private RecyclerViewClickListener listener;

    public DashboardFragment() {
        super();
    }

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstances(View rootView, Bundle savedInstanceState) {

        recyclerView = rootView.findViewById(R.id.recycler_view_dashboard);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        courseList = new ArrayList<>();

        //Test RecyclerView >>>
//        for(int i = 0; i<=10;i++){
//            CourseDao courseDao = new CourseDao(
//                    "heading" +i+1,
//                    "Heading",
//                    "Lorem"
//            );
//            courseList.add(courseDao);
//        }
//        adapter = new CourseAdapter(courseList,getContext());
//        recyclerView.setAdapter(adapter);
        //Test RecyclerView <<<

        loadData();


    }

    private void loadData() {
        courseList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                jsonUrl,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array = jsonObject.getJSONArray("data");

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            CourseDao item = new CourseDao(
                                    obj.getString("id"),
                                    obj.getString("CourseName"),
                                    obj.getString("Date")
                            );
                            courseList.add(item);
                        }

                        adapter = new CourseAdapter(courseList, getContext(), listener);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show());

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {

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
