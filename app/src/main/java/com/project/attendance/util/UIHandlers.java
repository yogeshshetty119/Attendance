package com.project.attendance.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Shashanth
 */

public final class UIHandlers {

    private static ProgressDialog dialog;

    private UIHandlers() {
    }

    public static void showProgress(Context context, String message) {
        if (dialog == null) {
            dialog = new ProgressDialog(context);
        }
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void dismissProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        dialog = null;
    }

    public static void showDialog(Context context, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Info");
        builder.setMessage(message);
        builder.setPositiveButton("OK", listener);
        builder.show();
    }

    public static void shortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showConfirmation(Context context, String message, final DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (listener != null) listener.onClick(dialogInterface, which);
            }
        });
        builder.setNegativeButton("CANCEL", null);
        builder.show();
    }

    public static void showConfirmation(Context context, String title, String message, String posButtonText,
                                        String negButtonText, final OnDialogClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(posButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listener != null) listener.positiveClick();
            }
        });
        builder.setNegativeButton(negButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listener != null) listener.negativeClick();
            }
        });
        builder.show();
    }

    public interface OnDialogClickListener {
        void positiveClick();
        void negativeClick();
    }
}
