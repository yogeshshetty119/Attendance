package com.project.attendance.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shashanth
 */

public class AttendanceWrapper {

    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private String errorMessage;
    @SerializedName("data")
    private List<Attendance> attendanceList;

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }
}
