package com.project.attendance.networking;

/**
 * Created by Shashanth
 */

public final class NetworkConstants {

    private NetworkConstants() {
    }

    public static final int CONNECT_TIME_OUT = 60;
    public static final int WRITE_TIME_OUT = 60;
    public static final int READ_TIME_OUT = 60;

    public static final String SERVER_IP = "192.168.43.237";
    public static final int SERVER_PORT = 8084;
    public static final String BASE_URL = "http://" + SERVER_IP + ":" + SERVER_PORT + "/attendance/";
}
