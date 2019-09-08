package com.project.attendance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.project.attendance.R;
import com.project.attendance.adapters.AllotmentsAdapter;
import com.project.attendance.model.Allotment;
import com.project.attendance.model.AllotmentWrapper;
import com.project.attendance.model.RVClickListener;
import com.project.attendance.networking.RequestListener;
import com.project.attendance.networking.RetrofitClient;
import com.project.attendance.util.Constants;

import java.util.List;

/**
 * Created by Shashanth
 */

public class AllotmentActivity extends AppBaseActivity {

    private static final String TAG = "AllotmentActivity";
    private final AllotmentActivity ctxt = AllotmentActivity.this;
    private Toolbar toolbar;
    private RecyclerView rvDepartments;
    private TextView infoView, lblWelcome;

    private List<Allotment> allotmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allotments);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Allotted Depts/Subs");
        rvDepartments = (RecyclerView) findViewById(R.id.recycler_view);
        rvDepartments.addItemDecoration(new DividerItemDecoration(ctxt, LinearLayoutManager.VERTICAL));
        lblWelcome = (TextView) findViewById(R.id.lbl_fac_name);
        infoView = (TextView) findViewById(R.id.text_view_info);
        infoView.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String facName = getUserDetails().getFacName();
        lblWelcome.setText(getString(R.string.fac_welcome, facName));

        if (allotmentList == null || allotmentList.size() == 0)
            loadAllotments();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (allotmentList != null)
            allotmentList.clear();
    }

    private void loadAllotments() {

        showProgressDialog("Loading...");

        new RetrofitClient().getAllotments(
                getFacRid(),
                new RequestListener<AllotmentWrapper>() {
                    @Override
                    public void onSuccessResponse(AllotmentWrapper wrapper) {
                        dismissDialog();
                        processAllotmentData(wrapper);
                    }

                    @Override
                    public void onFailureResponse(Throwable t) {
                        dismissDialog();
                        log(TAG, t);
                    }
                }
        );
    }

    private void processAllotmentData(AllotmentWrapper wrapper) {
        if (wrapper != null) {
            if ("OK".equals(wrapper.getStatus())) {
                allotmentList = wrapper.getAllotmentList();

                initAllotmentsRvAdapter();

            } else if ("NOT_OK".equals(wrapper.getStatus())) {
                toast(TOAST_LONG, wrapper.getErrorMessage());
            }
        } else {
            toast(TOAST_LONG, "Something went wrong");
        }
    }

    private void initAllotmentsRvAdapter() {
        if (allotmentList != null && allotmentList.size() != 0) {
            infoView.setVisibility(View.GONE);
            rvDepartments.setVisibility(View.VISIBLE);

            AllotmentsAdapter adapter = new AllotmentsAdapter(
                    allotmentList,
                    new RVClickListener<Allotment>() {
                        @Override
                        public void onClick(int position, Allotment allotment) {
                            gotoAttendanceActivity(allotment);
                        }
                    });

            rvDepartments.setAdapter(adapter);

        } else {
            infoView.setVisibility(View.VISIBLE);
            rvDepartments.setVisibility(View.GONE);
            infoView.setText(R.string.no_subjects_info_text);
        }
    }

    private void gotoAttendanceActivity(Allotment allotment) {
        Intent intent = new Intent(ctxt, AttendanceActivity.class);
        intent.putExtra(Constants.DEPT_ID_KEY, allotment.getDeptId());
        intent.putExtra(Constants.SEM_KEY, allotment.getSem());
        intent.putExtra(Constants.SUB_ID_KEY, allotment.getSubId());
        startActivityForResult(intent, Constants.START_ACTIVITY_CODE);
    }
}
