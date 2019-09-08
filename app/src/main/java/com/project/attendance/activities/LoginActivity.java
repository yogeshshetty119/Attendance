package com.project.attendance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.attendance.R;
import com.project.attendance.model.UserWrapper;
import com.project.attendance.networking.RequestListener;
import com.project.attendance.networking.RetrofitClient;
import com.project.attendance.util.Util;

/**
 * Created by Shashanth
 */

public class LoginActivity extends AppBaseActivity {

    private static final String TAG = "LoginActivity";
    private final LoginActivity ctxt = LoginActivity.this;

    private EditText etContactNo;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etContactNo = (EditText) findViewById(R.id.et_contact_no);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        login();
                    }
                }
        );
    }

    private void login() {
        if (!isValidInputs()) {
            return;
        }

        showProgressDialog("Logging in...");

        new RetrofitClient().loginUser(
                getContactNo(),
                new RequestListener<UserWrapper>() {
                    @Override
                    public void onSuccessResponse(UserWrapper wrapper) {
                        dismissDialog();
                        processLoginResponse(wrapper);
                    }

                    @Override
                    public void onFailureResponse(Throwable t) {
                        dismissDialog();
                        log(TAG, t);
                    }
                }
        );
    }

    private void processLoginResponse(UserWrapper wrapper) {
        if (wrapper != null) {
            if ("OK".equals(wrapper.getStatus())) {

                Util.saveUser(ctxt, wrapper.getUser());
                startActivity(new Intent(ctxt, AllotmentActivity.class));
                finish();

            } else if ("NOT_OK".equals(wrapper.getStatus())) {
                toast(TOAST_LONG, wrapper.getErrorMessage());
            }
        } else {
            toast(TOAST_LONG, "Something went wrong");
        }
    }

    private String getContactNo() {
        return etContactNo.getText().toString();
    }

    public boolean isValidInputs() {
        if (TextUtils.isEmpty(getContactNo()) || getContactNo().length() != 10) {
            toast(TOAST_SHORT, "Please enter valid contact number");
            return false;
        }

        return true;
    }
}
