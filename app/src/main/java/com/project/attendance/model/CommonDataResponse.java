package com.project.attendance.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shashanth
 */

public class CommonDataResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private String errorMessage;
    @SerializedName("data")
    private String message;

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getMessage() {
        return message;
    }
}
