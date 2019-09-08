package com.project.attendance.networking;

import com.project.attendance.model.AllotmentWrapper;
import com.project.attendance.model.AttendanceWrapper;
import com.project.attendance.model.CommonDataResponse;
import com.project.attendance.model.StudentWrapper;
import com.project.attendance.model.UserWrapper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shashanth
 */

public class RetrofitClient {

    private final ApiService apiService;

    public RetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // logging

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(NetworkConstants.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(NetworkConstants.WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(NetworkConstants.READ_TIME_OUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public void loginUser(String contactNumber, RequestListener<UserWrapper> listener) {
        Call<UserWrapper> call = apiService.loginUser("login", contactNumber);
        call.enqueue(new RequestCallBack<>(listener));
    }

    public void getAllotments(int facRid, RequestListener<AllotmentWrapper> listener) {
        Call<AllotmentWrapper> call = apiService.getAllotments("getAllotments", facRid);
        call.enqueue(new RequestCallBack<>(listener));
    }

    public void getStudents(int deptId, int sem, RequestListener<StudentWrapper> listener) {
        Call<StudentWrapper> call = apiService.getStudentsList("getStudentsList", deptId, sem);
        call.enqueue(new RequestCallBack<>(listener));
    }

    public void getAttendanceDetails(int studId, int sem, int subId, RequestListener<AttendanceWrapper> listener) {
        Call<AttendanceWrapper> call = apiService.getAttendanceDetails("getAttendanceDetails", studId,subId, sem);
        call.enqueue(new RequestCallBack<>(listener));
    }

    public void saveAttendance(String presentStudRids, String absentStudRids, int sem, int deptId, int subRid, int facRid,
                               RequestListener<CommonDataResponse> listener) {
        Call<CommonDataResponse> call = apiService.saveAttendance("saveAttendance", presentStudRids,
                absentStudRids, sem, deptId, subRid, facRid);
        call.enqueue(new RequestCallBack<>(listener));
    }
}
