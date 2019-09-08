package com.project.attendance.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shashanth
 */

public class UserWrapper {

    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private String errorMessage;
    @SerializedName("data")
    private User user;

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public User getUser() {
        return user;
    }
}
