package com.project.attendance.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.project.attendance.R;
import com.project.attendance.adapters.StudentListAdapter;
import com.project.attendance.model.CommonDataResponse;
import com.project.attendance.model.RVClickListener;
import com.project.attendance.model.Student;
import com.project.attendance.model.StudentWrapper;
import com.project.attendance.networking.RequestListener;
import com.project.attendance.networking.RetrofitClient;
import com.project.attendance.util.Constants;
import com.project.attendance.util.UIHandlers;

import java.util.List;

/**
 * Created by Shashanth
 */

public class AttendanceActivity extends AppBaseActivity {

    private static final String TAG = "AttendanceActivity";
    private AttendanceActivity ctxt = AttendanceActivity.this;
    private FloatingActionButton fabDoneAttendance;
    private RecyclerView rvStudents;
    private Toolbar toolbar;

    private List<Student> studentList;
    private static int departmentId = 0, sem = 0, subId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Students");

        rvStudents = (RecyclerView) findViewById(R.id.recycler_view);
        rvStudents.addItemDecoration(new DividerItemDecoration(ctxt, LinearLayoutManager.VERTICAL));
        fabDoneAttendance = (FloatingActionButton) findViewById(R.id.fab_done_attendance);

        fabDoneAttendance.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UIHandlers.showConfirmation(
                                ctxt,
                                "Are you sure want to save this? Action performed cannot be reverted. Confirm it before continuing.",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        doneWithAttendance();
                                    }
                                }
                        );
                    }
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        getStudentsList();
    }

    private void getStudentsList() {

        departmentId = getIntent().getIntExtra(Constants.DEPT_ID_KEY, 0);
        sem = getIntent().getIntExtra(Constants.SEM_KEY, 0);
        subId = getIntent().getIntExtra(Constants.SUB_ID_KEY, 0);

        showProgressDialog("Loading...");

        new RetrofitClient().getStudents(
                departmentId,
                sem,
                new RequestListener<StudentWrapper>() {
                    @Override
                    public void onSuccessResponse(StudentWrapper wrapper) {
                        dismissDialog();
                        processStudentsRecord(wrapper);
                    }

                    @Override
                    public void onFailureResponse(Throwable t) {
                        dismissDialog();
                        log(TAG, t);
                    }
                }
        );
    }

    private void processStudentsRecord(StudentWrapper wrapper) {
        if (wrapper != null) {
            if ("OK".equals(wrapper.getStatus())) {

                studentList = wrapper.getStudentList();
                initStudentsRvAdapter();

            } else if ("NOT_OK".equals(wrapper.getStatus())) {
                toast(TOAST_LONG, wrapper.getErrorMessage());
            }
        }
    }

    private void initStudentsRvAdapter() {
        if (studentList != null && studentList.size() != 0) {
            StudentListAdapter adapter = new StudentListAdapter(
                    studentList,
                    new RVClickListener<Student>() {
                        @Override
                        public void onClick(int position, Student student) {
                            Intent intent = new Intent(ctxt, AttendanceDetailsActivity.class);
                            intent.putExtra(Constants.SEM_KEY, sem);
                            intent.putExtra(Constants.SUB_ID_KEY, subId);
                            intent.putExtra(Constants.STUD_NAME, student.getStudentName());
                            intent.putExtra(Constants.STUD_ID, student.getStudentRid());
                            startActivity(intent);
                        }
                    });
            rvStudents.setAdapter(adapter);
        }
    }

    private void doneWithAttendance() {

        String presentStudentIds = "", absentStudIds = "";

        if (studentList != null && studentList.size() > 0) {
            for (Student student : studentList) {
                if (student.isSelected()) {
                    presentStudentIds += student.getStudentRid() + "#";
                } else {
                    absentStudIds += student.getStudentRid() + "#";
                }
            }
        }

        if (presentStudentIds.length() > 0) {
            presentStudentIds = presentStudentIds.substring(0, (presentStudentIds.length() - 1)); // remove last # character
        }

        if (absentStudIds.length() > 0) {
            absentStudIds = absentStudIds.substring(0, (absentStudIds.length() - 1));
        }

        showProgressDialog("Saving...");

        new RetrofitClient().saveAttendance(
                presentStudentIds,
                absentStudIds,
                sem,
                departmentId,
                subId,
                getFacRid(),
                new RequestListener<CommonDataResponse>() {
                    @Override
                    public void onSuccessResponse(CommonDataResponse response) {
                        dismissDialog();
                        processSaveAttendanceResponse(response);
                    }

                    @Override
                    public void onFailureResponse(Throwable t) {
                        dismissDialog();
                        log(TAG, t);
                    }
                }
        );

    }

    private void processSaveAttendanceResponse(CommonDataResponse response) {
        if (response != null) {
            if ("OK".equals(response.getStatus())) {
                toast(TOAST_SHORT, response.getMessage());
                finish();
            } else if ("NOT_OK".equals(response.getStatus())) {
                toast(TOAST_LONG, response.getErrorMessage());
            }
        } else {
            toast(TOAST_LONG, "Something went wrong.");
        }
    }
}
