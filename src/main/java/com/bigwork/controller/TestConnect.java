package com.bigwork.controller;

public class TestConnect {

    private static boolean connect = true;

    public static void setConnectionState(boolean connect) {
        TestConnect.connect = connect;
    }

    public static boolean getConnectionState() {
        return TestConnect.connect;
    }
}
