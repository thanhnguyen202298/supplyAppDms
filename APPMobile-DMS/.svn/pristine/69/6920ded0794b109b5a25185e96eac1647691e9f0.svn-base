package com.example.sskey.dms.CustomerFragment;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sskey.dms.MainActivity;
import com.example.sskey.dms.Order.OnScrollCallBack;
import com.example.sskey.dms.Order.OrderList;
import com.example.sskey.dms.R;
import com.example.sskey.dms.SanPhamActivity;
import com.example.sskey.dms.Utils.UtilBasic;
import com.sskey.dms.data.remote.DataRemoteHelper;
import com.sskey.dms.model.Customer;
import com.sskey.dms.utils.Constants;
import com.sskey.dms.utils.PreferenceUtils;

import java.util.List;

import retrofit2.Response;

public class CustomerFrag extends Fragment implements DataRemoteHelper.CallListCustomer, CustomerRCadapter.CustomerClickCb {

    private EditText seark;
    private RecyclerView customerlist;
    private DataRemoteHelper dbcon;
    private String saleman;
    private CustomerRCadapter ada;
    private Context ctx;
    private Button btnSeak;

    public CustomerFrag() {
    }

    private void initialized() {

//        final InputMethodManager im = (InputMethodManager) seark.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        im.hideSoftInputFromWindow(seark.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);

        btnSeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ada.filter(seark.getText().toString());

            }
        });

        saleman = PreferenceUtils.getLoaiNguoiDung(getContext());
        dbcon = new DataRemoteHelper(this);
        ada = new CustomerRCadapter(this);
        LinearLayoutManager m = new LinearLayoutManager(getContext());
        m.setOrientation(LinearLayoutManager.VERTICAL);

        customerlist.setLayoutManager(m);
        customerlist.setAdapter(ada);
        customerlist.setHasFixedSize(true);

        OnScrollCallBack adascroll = new OnScrollCallBack(20) {
            @Override
            public Boolean loadMore(int page, int totalItems, RecyclerView v) {
                load(page);
                return true;
            }
        };
        adascroll.setLayout(m);
        customerlist.setOnScrollListener(adascroll);

    }

    private void load(int page) {
        dbcon.getCustomerBySaler(page, saleman);
    }

    @Override
    public void onResume() {
        super.onResume();
        initialized();
        load(1);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.customer_list, container, false);

        seark = view.findViewById(R.id.edt_seark);
        btnSeak = view.findViewById(R.id.btnsearch);
        customerlist = view.findViewById(R.id.custlist);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ctx = context;
    }


    @Override
    public void Succeed(Response e) {
        if (e.message().equals("OK"))
            ada.update((List<Customer>) e.body());
        else Toast.makeText(ctx, e.message(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void Fail(Throwable t) {
        Toast.makeText(ctx, "lỗi kết nối", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotoSale(Customer customer) {
        Intent sanpham = new Intent(getContext(), SanPhamActivity.class);
        String d = UtilBasic.ObjectToJson(customer);
        sanpham.putExtra(Constants.CUSTOMER, d);
        startActivity(sanpham);
    }

    @Override
    public void viewOrder(String custId) {
        MainActivity mm = (MainActivity) getActivity();
        OrderList ol = OrderList.newInstance(custId, null);
        mm.loadFragment(ol);
        mm.setOrderMenu();
    }
}
