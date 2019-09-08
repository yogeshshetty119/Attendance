package com.project.attendance.networking;

/**
 * Created by Shashanth
 */

public interface RequestListener<T> {
    void onSuccessResponse(T t);
    void onFailureResponse(Throwable t);
}
