package com.project.attendance.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shashanth
 */

public class Attendance {

    @SerializedName("no_of_attendance")
    private int noOfAttendance;
    @SerializedName("no_of_class")
    private int noOfClass;

    public int getNoOfAttendance() {
        return noOfAttendance;
    }

    public int getNoOfClass() {
        return noOfClass;
    }
}
