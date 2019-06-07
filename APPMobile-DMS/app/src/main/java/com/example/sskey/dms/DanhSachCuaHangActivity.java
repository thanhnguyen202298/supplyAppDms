package com.example.sskey.dms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.sskey.dms.adapter.DSCuaHangAdapter;
import com.sskey.dms.data.remote.APIUtils;
import com.sskey.dms.data.remote.SOService;
import com.sskey.dms.model.PriceList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachCuaHangActivity extends AppCompatActivity {

    SOService mService;
    RecyclerView recyclerViewSP;
    DSCuaHangAdapter recycleAdapter;
    List<PriceList> lstPrice;
    String idItem;

    TextView txtTenSP, txtLoai, txtCode, txtGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_cua_hang);

        //init api
        mService = APIUtils.getSOService();

        lstPrice = new ArrayList<>();

        //init view
        recyclerViewSP = findViewById(R.id.rv_cuahang);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSP.setLayoutManager(layoutManager);
        recycleAdapter = new DSCuaHangAdapter(this, lstPrice);
        recyclerViewSP.setAdapter(recycleAdapter);
        recyclerViewSP.setHasFixedSize(true);

        TextView txtTenSP = findViewById(R.id.nameSP);
        txtTenSP.setText(getIntent().getStringExtra("NAMEITEM"));

        idItem = getIntent().getStringExtra("IDITEM");


        loadDSCuaHang();

    }

    private void loadDSCuaHang() {
        mService.getPriceList(idItem).enqueue(new Callback<List<PriceList>>() {
            @Override
            public void onResponse(Call<List<PriceList>> call, Response<List<PriceList>> response) {

                //check response
                if (response.isSuccessful()) {

                    recycleAdapter.updatePriceList(response.body());
                    Log.d("SanPhamActivity", "posts loaded from API " + lstPrice.toString());

                } else {
                    lstPrice = new ArrayList<>();
                    lstPrice.add(new PriceList("1223", "Nhóm", "Nhóm Sài Gòn", "DO", 100000, "", ""));
                    lstPrice.add(new PriceList("1223", "Nhóm", "Nhóm Sài Gòn", "DO", 100000, "", ""));
                    lstPrice.add(new PriceList("1223", "Nhóm", "Nhóm Sài Gòn", "DO", 100000, "", ""));
                    lstPrice.add(new PriceList("1223", "Nhóm", "Nhóm Sài Gòn", "DO", 100000, "", ""));
                    lstPrice.add(new PriceList("1223", "Nhóm", "Nhóm Sài Gòn", "DO", 100000, "", ""));
                    lstPrice.add(new PriceList("1223", "Nhóm", "Nhóm Sài Gòn", "DO", 100000, "", ""));
                    lstPrice.add(new PriceList("1223", "Nhóm", "Nhóm Sài Gòn", "DO", 100000, "", ""));
                    lstPrice.add(new PriceList("1223", "Nhóm", "Nhóm Sài Gòn", "DO", 100000, "", ""));

                    recycleAdapter.updatePriceList(lstPrice);
                    StyleableToast.makeText(getBaseContext(), "Đây là danh sách sản phẩm thử", R.style.errorserverToast).show();
                    Log.e("SanPhamActivity", "API not ok");
                }
            }

            @Override
            public void onFailure(Call<List<PriceList>> call, Throwable t) {
                StyleableToast.makeText(DanhSachCuaHangActivity.this, "Không kết nối được với server, Vui lòng kiểm tra lại", R.style.errorserverToast).show();
                Log.d("SanPhamActivity", "error loading from API: " + t.toString());
            }
        });
    }
}
