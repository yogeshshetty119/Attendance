package com.project.attendance.networking;

import com.project.attendance.model.AllotmentWrapper;
import com.project.attendance.model.AttendanceWrapper;
import com.project.attendance.model.CommonDataResponse;
import com.project.attendance.model.StudentWrapper;
import com.project.attendance.model.UserWrapper;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Shashanth
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("AndroidAPIServlet")
    Call<UserWrapper> loginUser(
            @Field("command") String command,
            @Field("contactNumber") String contactNumber
    );

    @GET("AndroidAPIServlet")
    Call<AllotmentWrapper> getAllotments(
            @Query("command") String command,
            @Query("facRid") int facRid
    );

    @GET("AndroidAPIServlet")
    Call<StudentWrapper> getStudentsList(
            @Query("command") String command,
            @Query("deptId") int deptId,
            @Query("sem") int sem
    );

    @FormUrlEncoded
    @POST("AndroidAPIServlet")
    Call<CommonDataResponse> saveAttendance(
            @Field("command") String command,
            @Field("presentStudRids") String presentStudRids,
            @Field("absentStudRids") String absentStudRids,
            @Field("sem") int sem,
            @Field("deptId") int deptId,
            @Field("subRid") int subRid,
            @Field("facRid") int facRid
    );

    @GET("AndroidAPIServlet")
    Call<AttendanceWrapper> getAttendanceDetails(
            @Query("command") String getStudentsList,
            @Query("studId") int studId,
            @Query("subId") int subId,
            @Query("sem") int sem
    );
}
