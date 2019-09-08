package com.project.attendance.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shashanth
 */

public class Allotment {

    @SerializedName("al_rid")
    private int allotmentRid;
    @SerializedName("al_staff_rid")
    private int staffRid;
    @SerializedName("al_dept")
    private int deptId;
    @SerializedName("al_sub_rid")
    private int subId;
    @SerializedName("sub_name")
    private String subName;
    @SerializedName("dd_value")
    private String department;
    @SerializedName("sub_sem")
    private int sem;

    public String getSubName() {
        return subName;
    }

    public String getDepartment() {
        return department;
    }

    public int getAllotmentRid() {
        return allotmentRid;
    }

    public int getStaffRid() {
        return staffRid;
    }

    public int getDeptId() {
        return deptId;
    }

    public int getSubId() {
        return subId;
    }

    public int getSem() {
        return sem;
    }

    public String getSemText() {
        if (sem == 1)
            return "1st sem";
        else if (sem == 2)
            return "2nd sem";
        else if (sem == 3)
            return "3rd sem";
        else if (sem >= 4)
            return sem + "th sem";
        else return "";
    }
}

