package com.example.sskey.dms.OrderDetail;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sskey.dms.R;
import com.sskey.dms.data.remote.CallBack;
import com.sskey.dms.data.remote.DataRemoteHelper;
import com.sskey.dms.model.Customer;
import com.sskey.dms.model.Order;
import com.sskey.dms.model.SaleLine;

import java.util.List;

import retrofit2.Response;

public class StatusViewDialg {
    private TextView yes;
    private TextView txtmaDH;
    private Spinner myspiner;
    private Spinner StaffSpiner;
    private LinearLayout StaffSelector;
    private EditText editTexNote;

    private int rank = 0;
    private boolean selected = false;
    DataRemoteHelper dbhelp;

    public void showDialog(final Context activity, final int userType, List<Customer> StaffList, final Order donHang_master, final dismissLisstener listener) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.status_odetail_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        yes = dialog.findViewById(R.id.btnRanking);
        txtmaDH = dialog.findViewById(R.id.txt_MaDonHang);
        txtmaDH.setText(donHang_master.saleID);
        myspiner = dialog.findViewById(R.id.spinner);
        StaffSpiner = dialog.findViewById(R.id.spinner3);
        StaffSelector = dialog.findViewById(R.id.StaffSelector);
        editTexNote = dialog.findViewById(R.id.edt_note);

        ArrayAdapter<Customer> staffada = new ArrayAdapter<Customer>(activity, R.layout.item_spinner_sskey, StaffList);
        staffada.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity, R.array.status_array, R.layout.item_spinner_sskey);
        if (userType == 3)
            adapter = ArrayAdapter.createFromResource(activity, R.array.status_array_post, R.layout.item_spinner_sskey);
        else if (userType == 0) {
            adapter = ArrayAdapter.createFromResource(activity, R.array.status_array_shop, R.layout.item_spinner_sskey);
            if (StaffList != null) {
                StaffSpiner.setAdapter(staffada);
            }
        }
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        myspiner.setAdapter(adapter);

        myspiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 && userType == 0) {
                    StaffSelector.setVisibility(View.VISIBLE);
                    selected = false;
                } else {
                    StaffSelector.setVisibility(View.GONE);
                    selected = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        StaffSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dbhelp = new DataRemoteHelper(new CallBack() {
            @Override
            public void Succeed(Response e) {
                Toast.makeText(dialog.getContext(), "Đã cập nhật " + e.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void Fail(Throwable t) {
                Toast.makeText(dialog.getContext(), "Fail: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selected) {
                    Toast.makeText(activity, "Vui lòng chọn nhân viên giao hàng", Toast.LENGTH_LONG).show();
                    return;
                }
                donHang_master.saleStatus = myspiner.getSelectedItem().toString();


                dbhelp.updateOrder(donHang_master);
                dialog.dismiss();
                listener.setText(donHang_master.saleStatus);
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.setOnDismissListener(listener);

        //dialog.setCanceledOnTouchOutside(true);
    }

    public interface dismissLisstener extends Dialog.OnDismissListener {
        @Override
        void onDismiss(DialogInterface dialog);

        void setText(String myresult);
    }

}