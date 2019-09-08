package com.project.attendance.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shashanth
 */

public class Student {

    @SerializedName("stud_rid")
    private int studentRid;
    @SerializedName("stud_name")
    private String studentName;
    @SerializedName("stud_branch")
    private int studentDept;
    @SerializedName("stud_reg_no")
    private String regNo;

    private boolean isSelected = true;

    public int getStudentRid() {
        return studentRid;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentDept() {
        return studentDept;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getRegNo() {
        return regNo;
    }
}
