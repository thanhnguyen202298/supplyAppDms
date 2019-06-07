package com.sskey.dms.data.remote;

import com.sskey.dms.model.Customer;
import com.sskey.dms.model.Order;
import com.sskey.dms.model.SaleLine;
import com.sskey.dms.model.SanPham;
import com.sskey.dms.model.UserInFoDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRemoteHelper {

    public SOService service;

    public DataRemoteHelper(CallBack callBack) {
        service = APIUtils.getSOService();
        ctmCall = callBack;
    }

    //customer
    CallBack ctmCall;

    public void getCustomerList(int page) {
        service.getCustomerList(page).enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    public void getCustomerBySaler(int page, String manid) {
        service.getCustomerBySale(page, manid).enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    public void getCustomerById(String customercode) {
        service.getCustomerById(customercode).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    public void doLogin(String sdt, String pas) {
        service.login(sdt, pas).enqueue(new Callback<UserInFoDTO>() {
            @Override
            public void onResponse(Call<UserInFoDTO> call, Response<UserInFoDTO> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<UserInFoDTO> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    public void doUpdateCustomer(UserInFoDTO kh) {
        service.updateCustomer(kh).enqueue(new Callback<SucceedUpdate>() {
            @Override
            public void onResponse(Call<SucceedUpdate> call, Response<SucceedUpdate> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<SucceedUpdate> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }
//
//    public void saveCustomer(String usn, String sdt) {
//        service.saveCustomer(usn, sdt).enqueue(new Callback<Customer>() {
//            @Override
//            public void onResponse(Call<Customer> call, Response<Customer> response) {
//                ctmCall.Succeed(response);
//            }
//
//            @Override
//            public void onFailure(Call<Customer> call, Throwable t) {
//                ctmCall.Fail(t);
//            }
//        });
//    }

    // order
    public void getOrderList(int page, String manid, String status) {
        service.getOrderList(page, manid, status).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    public void getOrderById(String id) {
        service.getOrderById(id).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    public void saveOrder(Order dh) {
        service.saveOrder(dh).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    public void updateOrder(Order dh) {
        service.update(dh).enqueue(new Callback<SucceedUpdate>() {
            @Override
            public void onResponse(Call<SucceedUpdate> call, Response<SucceedUpdate> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<SucceedUpdate> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    //products
    public void getProductList() {
        service.getProductList().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    public void getProductById(String id) {
        service.getProductById(id).enqueue(new Callback<SanPham>() {
            @Override
            public void onResponse(Call<SanPham> call, Response<SanPham> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<SanPham> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    public void updateProduct(SanPham sp) {
        service.updateProduct(sp).enqueue(new Callback<SucceedUpdate>() {
            @Override
            public void onResponse(Call<SucceedUpdate> call, Response<SucceedUpdate> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<SucceedUpdate> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    public void saveProduct(SanPham sp) {
        service.saveProduct(sp).enqueue(new Callback<SanPham>() {
            @Override
            public void onResponse(Call<SanPham> call, Response<SanPham> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<SanPham> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    public void getDetails(String idsale) {
        service.getDetails(idsale).enqueue(new Callback<List<SaleLine>>() {
            @Override
            public void onResponse(Call<List<SaleLine>> call, Response<List<SaleLine>> response) {
                ctmCall.Succeed(response);
            }

            @Override
            public void onFailure(Call<List<SaleLine>> call, Throwable t) {
                ctmCall.Fail(t);
            }
        });
    }

    ////

    public interface CallListCustomer extends CallBack {
    }

    public interface CallListOrder extends CallBack {
    }

    public interface CallDetailOrder extends CallBack {
    }

}