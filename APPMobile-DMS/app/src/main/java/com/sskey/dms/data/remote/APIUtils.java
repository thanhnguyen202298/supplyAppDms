package com.sskey.dms.data.remote;

public class APIUtils {
//    public static final String BASE_URL = "http://192.168.1.117:50311";
//    public static final String BASE_URL = "http://125.253.113.123:9098";
//    public static final String BASE_URL = "http://192.168.1.110:100";
    public static final String BASE_URL = "http://192.168.1.110:8686";
//    public static final String BASE_URL = "http://192.168.1.130:800";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}