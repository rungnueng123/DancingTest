package com.mocom.com.dancingtest.Model.dao;

public class CourseDao {

    public CourseDao() {

    }

    private String id;
    private String courseName;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CourseDao(String id, String courseName, String date) {
        this.id = id;
        this.courseName = courseName;
        this.date = date;
    }

}
