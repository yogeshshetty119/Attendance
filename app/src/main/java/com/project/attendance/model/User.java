package com.project.attendance.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shashanth
 */

public class User {

    @SerializedName("fac_name")
    private String facName;
    @SerializedName("fac_dept")
    private int facDept;
    @SerializedName("fac_dob")
    private String facDob;
    @SerializedName("fac_rid")
    private int facRid;
    @SerializedName("fac_contact")
    private String facContact;
    @SerializedName("fac_doj")
    private String facDoj;
    @SerializedName("fac_is_active")
    private int facIsActive;
    @SerializedName("fac_email_id")
    private String facEmailId;
    @SerializedName("fac_address")
    private String facAddress;

    public String getFacName() {
        return facName;
    }

    public int getFacDept() {
        return facDept;
    }

    public String getFacDob() {
        return facDob;
    }

    public int getFacRid() {
        return facRid;
    }

    public String getFacContact() {
        return facContact;
    }

    public String getFacDoj() {
        return facDoj;
    }

    public int getFacIsActive() {
        return facIsActive;
    }

    public String getFacEmailId() {
        return facEmailId;
    }

    public String getFacAddress() {
        return facAddress;
    }


}
