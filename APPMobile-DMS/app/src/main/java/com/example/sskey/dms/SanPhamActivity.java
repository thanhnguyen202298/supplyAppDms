package com.example.sskey.dms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sskey.dms.Order.OrderDetailActivity;
import com.example.sskey.dms.Utils.UtilBasic;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.sskey.dms.adapter.SanPhamRecycleAdapter;
import com.sskey.dms.data.remote.APIUtils;
import com.sskey.dms.data.remote.SOService;
import com.sskey.dms.model.Customer;
import com.sskey.dms.model.Order;
import com.sskey.dms.model.SaleLine;
import com.sskey.dms.model.SanPham;
import com.sskey.dms.utils.Constants;
import com.sskey.dms.utils.UpdateBadgeCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanPhamActivity extends AppCompatActivity implements UpdateBadgeCart {

    List<SanPham> sanPhamList;
    SOService mService;
    RecyclerView recyclerViewSP;
    FloatingActionButton flabCart;
    SanPhamRecycleAdapter recycleAdapter;
    TextView mBadge;

    SaleLine detail;
    Order master;
    HashMap<Integer, SaleLine> lstDHDT;
    Customer myCustomer;
    int position = 0;
    private ActionBar toolbar;
    private int count = 0;

    UpdateBadgeCart updateBadgeCart = this;

    public UpdateBadgeCart getUpdateBadgeCart() {
        return updateBadgeCart;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_sanpham);

        //Badge
        mBadge = (TextView) findViewById(R.id.badge);
        mBadge.setVisibility(View.GONE);

        //No action bar
//        toolbar = getSupportActionBar();
//        toolbar.hide();

        //init data
        sanPhamList = new ArrayList<>();
        detail = new SaleLine();
        master = new Order();
        lstDHDT = new HashMap<>();

        //init view
        flabCart = (FloatingActionButton) findViewById(R.id.fabCart);
        mService = APIUtils.getSOService();
        recyclerViewSP = (RecyclerView) findViewById(R.id.rv_sanpham);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSP.setLayoutManager(layoutManager);
        recycleAdapter = new SanPhamRecycleAdapter(getApplicationContext(), sanPhamList, updateBadgeCart);
        recyclerViewSP.setAdapter(recycleAdapter);
        recyclerViewSP.setHasFixedSize(true);

        flabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setDonHangChiTiets(recycleAdapter.getCart());
                    if (!master.saleLines.isEmpty()) {
                        if (myCustomer != null) {
                            master.shipTo = myCustomer.Address;
                            master.custAccount = myCustomer.CustAccount;
                            master.shopName = myCustomer.CustName;
                            master.shopPhone = myCustomer.Tell;
                        } else {
                            master.custAccount = null;
                            master.shopName = "Thành Gas";
                            master.shopPhone = "0123456789";
                        }
                        String obj = UtilBasic.ObjectToJson(master);
                        Intent cartIntent = new Intent(SanPhamActivity.this, OrderDetailActivity.class);
                        cartIntent.putExtra(Constants.OrderObj, obj);
                        cartIntent.putExtra(Constants.OrderRequest, Constants.NewOrder);
                        startActivity(cartIntent);
                    } else {

                        Toast.makeText(SanPhamActivity.this, "Chưa có sản phẩm", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(SanPhamActivity.this, e.getMessage() + "debug", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //customer 4 order Object
        String mm = getIntent().getStringExtra(Constants.CUSTOMER);
        myCustomer = UtilBasic.getGs().fromJson(mm, Customer.class);
        loadSanPham();
    }

    private void setDonHangChiTiets(HashMap<Integer, SaleLine> lstDHDT) {
        {
            master.saleLines = new ArrayList<>();
            for (Integer i : lstDHDT.keySet()) {
                master.saleLines.add(lstDHDT.get(i));
            }
        }
    }

    //load product for api
    private void loadSanPham() {
        int page = 1;
        mService.getProductList(page, myCustomer.CustAccount, myCustomer.CustGroup).enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {

                //check response
                if (response.isSuccessful()) {
//                    sanPhamList = response.body();
                    recycleAdapter.updateSanPham(response.body());
//                    adapter.notifyDataStateChanged()
                    Log.d("SanPhamActivity", "posts loaded from API " + sanPhamList.toString());

                } else {
                    Log.e("SanPhamActivity", "API not ok");
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                StyleableToast.makeText(SanPhamActivity.this, "Không kết nối được với server, Vui lòng kiểm tra lại", R.style.errorserverToast).show();
                Log.d("SanPhamActivity", "error loading from API: " + t.toString());
            }
        });
    }

    @Override
    public void updateBadge(int number) {
//        Toast.makeText(this,number+"",Toast.LENGTH_LONG).show();
        if (number > 0) {
            mBadge.setText(number + "");
            mBadge.setVisibility(View.VISIBLE);
        } else
            mBadge.setVisibility(View.GONE);
    }
}