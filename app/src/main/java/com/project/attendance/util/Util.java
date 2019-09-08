package com.project.attendance.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.ArrayRes;
import android.util.Patterns;

import com.google.gson.Gson;
import com.project.attendance.model.User;

/**
 * Created by Shashanth
 */

public final class Util {

    private static final String PREFS_NAME = "MY_APP_DATA";
    private static final String IP_KEY = "IP";
    private static final String USER_INFO_KEY = "USER_INFO";
    private static final String USER_RID_KEY = "USER_RID";
    private static final String MULTI_USER_REPORT_KEY = "MULTI_USER_REPORT";

    private static void saveAppData(Context ctx, String key, String value) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private static String getAppData(Context ctx, String key) {
        SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(key, "");
    }

    public static String getIp(Context context) {
        return getAppData(context, IP_KEY);
    }

    public static void setHasMultiUserReportAccess(Context context, boolean value) {
        saveAppData(context, MULTI_USER_REPORT_KEY, String.valueOf(value));
    }

    public static boolean hasMultiUserReportAccess(Context context) {
        return Boolean.parseBoolean(getAppData(context, MULTI_USER_REPORT_KEY));
    }

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void saveUser(Context context, String json) {
        saveAppData(context, USER_INFO_KEY, json);
        User user = new Gson().fromJson(json, User.class);
        int userRid = user != null ? user.getFacRid() : 0;
        saveAppData(context, USER_RID_KEY, String.valueOf(userRid));
    }

    public static void saveUser(Context context, User user) {
        saveUser(context, new Gson().toJson(user));
    }

    public static User getUser(Context context) {
        String userString = getAppData(context, USER_INFO_KEY);
        User user = new Gson().fromJson(userString, User.class);
        return user != null ? user : null;
    }

    public static int getUserRid(Context context) {
        return Integer.parseInt(getAppData(context, USER_RID_KEY).equals("") ? "0" : getAppData(context, USER_RID_KEY));
    }

    public static void setIp(Context context, String ip) {
        saveAppData(context, IP_KEY, ip);
    }

    public static String[] getStringArray(Context context, @ArrayRes int stringResId) {
        return context.getResources().getStringArray(stringResId);
    }
}
