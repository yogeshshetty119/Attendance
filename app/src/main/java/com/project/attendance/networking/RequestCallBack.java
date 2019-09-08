package com.project.attendance.networking;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shashanth
 */

public class RequestCallBack<T> implements Callback<T> {

    private RequestListener<T> listener;

    public RequestCallBack(RequestListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        listener.onSuccessResponse(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        listener.onFailureResponse(t);
    }
}
