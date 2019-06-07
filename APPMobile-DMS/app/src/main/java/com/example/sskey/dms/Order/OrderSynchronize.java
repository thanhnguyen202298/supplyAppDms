package com.example.sskey.dms.Order;

import android.content.Context;
import android.util.Log;

import com.example.sskey.dms.OrderDetail.IOrderSynchronize;
import com.example.sskey.dms.Utils.CallbackSSk;
import com.example.sskey.dms.Utils.UtilBasic;
import com.sskey.dms.data.remote.APIUtils;
import com.sskey.dms.data.remote.SOService;
import com.sskey.dms.model.Customer;
import com.sskey.dms.model.Order;
import com.sskey.dms.model.UserInFoDTO;
import com.sskey.dms.utils.Constants;
import com.sskey.dms.utils.PreferenceUtils;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderSynchronize implements IOrderSynchronize {

    private UserInFoDTO user;
    private Context ctx;
    private SOService vservice;
    CallbackSSk<List<Order>> myc;

    public OrderSynchronize(UserInFoDTO nguoiDung, Context context, CallbackSSk<List<Order>> callbackSSk) {
        ctx = context;
        user = nguoiDung;
        vservice = APIUtils.getSOService();
        myc = callbackSSk;
    }

    @Override
    public void readNewOrder() {
        user = UtilBasic.getUser(ctx);

        vservice.getNotifyByUser(user.getUserID()).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                myc.Success(response.body(), response.message()+" ::bạn mua");
                Log.e("<<Servicesss", "user dat hang");
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                myc.Fail(t);
            }
        });

    }

    @Override
    public String getSyncDate() {
        return PreferenceUtils.prefs.getString(Constants.SynchronizeDate, new Date().toGMTString());
    }

}
