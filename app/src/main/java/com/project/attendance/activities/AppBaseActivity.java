package com.project.attendance.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.project.attendance.model.User;
import com.project.attendance.util.UIHandlers;
import com.project.attendance.util.Util;

/**
 * Created by Shashanth
 */

public class AppBaseActivity extends AppCompatActivity {

    protected static final int TOAST_SHORT = Toast.LENGTH_SHORT;
    protected static final int TOAST_LONG = Toast.LENGTH_LONG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void toast(int length, String message) {
        Toast.makeText(getApplicationContext(), message, length).show();
    }

    // log error with a Toast message
    protected void log(String tag, Exception ex) {
        toast(TOAST_LONG, ex.getMessage());
        Log.e(tag, "Error : ", ex);
    }

    // log error with a Toast message
    protected void log(String tag, String message) {
        toast(TOAST_LONG, message);
        Log.i(tag, "Error : " + message);
    }

    protected void log(String tag, Throwable t) {
        toast(TOAST_LONG, t.getCause().getMessage());
        Log.e(tag, "Error : ", t);
    }

    protected void showProgressDialog(String message) {
        UIHandlers.showProgress(AppBaseActivity.this, message);
    }

    protected void dismissDialog() {
        UIHandlers.dismissProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIHandlers.dismissProgress();
    }

    protected int getFacRid() {
        return Util.getUserRid(AppBaseActivity.this);
    }

    protected User getUserDetails(){
        return Util.getUser(getApplicationContext());
    }
}
