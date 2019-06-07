package com.sskey.dms.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {
    public PreferenceUtils(){

    }
    public static void getSharesPrefer(Context context){
        if(prefs==null)
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferences prefs;

    public static boolean savePhone(String soDienThoai, Context context) {
        getSharesPrefer(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_PHONE, soDienThoai);
        prefsEditor.apply();
        return true;
    }

    public static String getPhone(Context context) {
        getSharesPrefer(context);
        return prefs.getString(Constants.KEY_PHONE, null);
    }

    public static boolean savePassword(String matKhau, Context context) {
        getSharesPrefer(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_PASSWORD, matKhau);
        prefsEditor.apply();
        return true;
    }

    public static String getPassword(Context context) {
        getSharesPrefer(context);
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.KEY_PASSWORD, null);
    }
    public static boolean saveMaNguoiDung(String maNguoiDung, Context context) {
        getSharesPrefer(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_MA, maNguoiDung);
        prefsEditor.apply();
        return true;
    }

    public static String getMaNguoiDung(Context context) {
        getSharesPrefer(context);
        return prefs.getString(Constants.KEY_MA, null);
    }
    public static boolean saveTenNguoiDung(String tenNguoiDung, Context context) {
        getSharesPrefer(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_TEN, tenNguoiDung);
        prefsEditor.apply();
        return true;
    }

    public static String getTenNguoiDung(Context context) {
        getSharesPrefer(context);
        return prefs.getString(Constants.KEY_TEN, null);
    }

    public static boolean saveLoaiNguoiDung(String loaiNguoiDung, Context context) {
        getSharesPrefer(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_LOAI, loaiNguoiDung);
        prefsEditor.apply();
        return true;
    }

    public static String getLoaiNguoiDung(Context context) {
        getSharesPrefer(context);
        return prefs.getString(Constants.KEY_LOAI, null);
    }

    public static boolean saveDiaChiNguoiDung(String diaChiNguoiDung, Context context) {
        getSharesPrefer(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_DIACHI, diaChiNguoiDung);
        prefsEditor.apply();
        return true;
    }

    public static String getDiaChiNguoiDung(Context context) {
        getSharesPrefer(context);
        return prefs.getString(Constants.KEY_DIACHI, null);
    }

    public static boolean saveObj(String soDienThoai, Context context) {
        getSharesPrefer(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_USER, soDienThoai);
        prefsEditor.apply();
        return true;
    }

    public static String getObj(Context context) {
        getSharesPrefer(context);
        return prefs.getString(Constants.KEY_USER, null);
    }

    public static String getMaxOrd(Context context) {
        getSharesPrefer(context);
        return prefs.getString(Constants.SynchronizeMaxord, "0");
    }

    public static boolean saveEmailNguoiDung(String mailNguoiDung, Context context) {
        getSharesPrefer(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_EMAILUSER, mailNguoiDung);
        prefsEditor.apply();
        return true;
    }

    public static String getMailNguoiDung(Context context) {
        getSharesPrefer(context);
        return prefs.getString(Constants.KEY_EMAILUSER, null);
    }

    public static boolean saveObjBLSum(String blSum, Context context) {
        getSharesPrefer(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_BLSUM, blSum);
        prefsEditor.apply();
        return true;
    }

    public static String getObjBLSum(Context context) {
        getSharesPrefer(context);
        return prefs.getString(Constants.KEY_BLSUM, null);
    }


    public static void saveBallanc(Context context, String ballancc) {
        getSharesPrefer(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_BALLAN, ballancc);
        prefsEditor.apply();
    }

    public static String getBallance(Context context) {
        getSharesPrefer(context);
        return prefs.getString(Constants.KEY_BALLAN, null);
    }

}
