package com.project.attendance.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shashanth
 */

public class StudentWrapper {

    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private String errorMessage;
    @SerializedName("data")
    private List<Student> studentList;

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
