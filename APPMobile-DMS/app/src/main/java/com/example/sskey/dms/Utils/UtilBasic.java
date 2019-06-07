package com.example.sskey.dms.Utils;

import android.content.Context;
import android.graphics.Color;

import com.example.sskey.dms.R;
import com.google.gson.Gson;
import com.sskey.dms.data.remote.APIUtils;
import com.sskey.dms.data.remote.SOService;
import com.sskey.dms.model.Customer;
import com.sskey.dms.model.Order;
import com.sskey.dms.model.UserInFoDTO;
import com.sskey.dms.utils.Constants;
import com.sskey.dms.utils.PreferenceUtils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilBasic {
    private static SimpleDateFormat sdf;
    private static NumberFormat numberFormat;
    private static Gson gs;
    private static UserInFoDTO user;

    public static SimpleDateFormat getSdf() {
        if (sdf == null) sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        return sdf;
    }
    public static SimpleDateFormat get_yyyyMMddHHmmss() {
        if (sdf == null) sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf;
    }
    public static String Date4Server(String ddmm) {
        if (ddmm==null)return "";
        String tm = ddmm.substring(3, 6);
        tm += ddmm.substring(0, 3);
        tm += ddmm.substring(6);
        return tm;
    }

    public static NumberFormat getNumberFormat() {
        if (numberFormat == null) numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
        return numberFormat;
    }

    public static String ObjectToJson(Object obj) {
        if (gs == null) gs = new Gson();
        return gs.toJson(obj);
    }

    public static Gson getGs() {
        if (gs == null) gs = new Gson();
        return gs;
    }

    public static UserInFoDTO getUser(final Context context) {

        getGs();
        String Obj = PreferenceUtils.getObj(context);
        if (user == null)
            user = gs.fromJson(Obj, UserInFoDTO.class);
        Obj = PreferenceUtils.getMaNguoiDung(context);
        if (Obj == null) return null;
        if (user != null) {
            SOService vservice = APIUtils.getSOService();
            vservice.getUsInfoById(Obj).enqueue(new Callback<UserInFoDTO>() {
                @Override
                public void onResponse(Call<UserInFoDTO> call, Response<UserInFoDTO> response) {
                    UserInFoDTO s = response.body();
                    if (s != null) {
                        if (s.getRole() != user.getRole() || s.getUserName() != user.getUserName()) {
                            user = s;
                            String u = UtilBasic.ObjectToJson(user);
                            PreferenceUtils.saveObj(u, context);
                        }
                    } else {
                        PreferenceUtils.prefs.edit().clear().apply();
                    }
                }

                @Override
                public void onFailure(Call<UserInFoDTO> call, Throwable t) {

                }
            });
        }
        return user;
    }

    public static CommonStatus getStatusOrder(String statuscode, Context ctx) {
        CommonStatus st = new CommonStatus();
        st.MyColor = Color.rgb(0, 0, 0);
        if (statuscode.equals(Constants.mDeliver)) {
            st.MyColor = ctx.getResources().getColor(R.color.Delivery);
        } else if (statuscode.equals(Constants.mFinished)) {
            st.MyColor = ctx.getResources().getColor(R.color.finish);
        } else if (statuscode.equals(Constants.inProgress)) {
            st.MyColor = ctx.getResources().getColor(R.color.inProcess);
        } else if (statuscode.equals(Constants.inDelete)) {
            st.MyColor = ctx.getResources().getColor(R.color.inProcess);
        }
        return st;
    }

    public static int getImageName(String name, Context ctx) {
        if (name == null) return -1;
        if (name.equalsIgnoreCase("XANHDUONG"))
            return R.drawable.bg;
        else if (name.equalsIgnoreCase("do"))
            return R.drawable.bgdo;
        else if (name.equalsIgnoreCase("VANG"))
            return R.drawable.bgvang;
        else if (name.equalsIgnoreCase("HONG"))
            return R.drawable.bghong;
        else if (name.equalsIgnoreCase("XANHLA"))
            return R.drawable.bgxanh;
        else if (name.equalsIgnoreCase("XAM"))
            return R.drawable.bgxam;

        return -1;
    }
}
