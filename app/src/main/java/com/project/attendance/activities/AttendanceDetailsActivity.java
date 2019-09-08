package com.project.attendance.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.project.attendance.R;
import com.project.attendance.model.Attendance;
import com.project.attendance.model.AttendanceWrapper;
import com.project.attendance.networking.RequestListener;
import com.project.attendance.networking.RetrofitClient;
import com.project.attendance.util.Constants;

public class AttendanceDetailsActivity extends AppBaseActivity {

    private static final String TAG = "AttendanceDetailsActivi";

    private TextView lblStudName, lblSubject, lblTotalClass, lblAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_details);

        lblStudName = (TextView) findViewById(R.id.lbl_stud_name);
        lblTotalClass = (TextView) findViewById(R.id.lbl_total_lass);
        lblAttendance = (TextView) findViewById(R.id.lbl_attendance);

        lblStudName.setText(getIntent().getStringExtra(Constants.STUD_NAME));

        getAttendanceDetails();
    }

    private void getAttendanceDetails() {

        int sem = getIntent().getIntExtra(Constants.SEM_KEY, 0);
        int subId = getIntent().getIntExtra(Constants.SUB_ID_KEY, 0);
        int studId = getIntent().getIntExtra(Constants.STUD_ID, 0);

        showProgressDialog("Loading...");

        new RetrofitClient().getAttendanceDetails(
                studId,
                sem,
                subId,
                new RequestListener<AttendanceWrapper>() {
                    @Override
                    public void onSuccessResponse(AttendanceWrapper wrapper) {
                        dismissDialog();
                        processAttendanceRecord(wrapper);
                    }

                    @Override
                    public void onFailureResponse(Throwable t) {
                        dismissDialog();
                        log(TAG, t);
                    }
                }
        );
    }

    private void processAttendanceRecord(AttendanceWrapper response) {
        if (response != null) {
            if ("OK".equals(response.getStatus())) {

                Attendance attendance = response.getAttendanceList().get(0);
                lblAttendance.setText("No of attendance : " + attendance.getNoOfAttendance());
                lblTotalClass.setText("Total classes : " + attendance.getNoOfClass());

            } else if ("NOT_OK".equals(response.getStatus())) {
                toast(TOAST_LONG, response.getErrorMessage());
            }
        }
    }
}
