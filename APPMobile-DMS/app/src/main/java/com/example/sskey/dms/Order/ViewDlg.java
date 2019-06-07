package com.example.sskey.dms.Order;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sskey.dms.Utils.UtilBasic;
import com.example.sskey.dms.R;
import com.sskey.dms.data.remote.CallBack;
import com.sskey.dms.data.remote.DataRemoteHelper;
import com.sskey.dms.model.Order;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ViewDlg {
    private TextView yes;
    private TextView txtmaDH;
    private TextView txttime;
    private TextView txttien;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private int rank = 0;
    private EditText note;
    DataRemoteHelper dbhelp;
    private List<ImageView> viewImg;

    public void showDialog(Context activity, final Order donHang_master) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.orderlist_rating_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        yes = dialog.findViewById(R.id.btnRanking);

        txtmaDH = dialog.findViewById(R.id.txt_MaDonHang);
        txttime = dialog.findViewById(R.id.txtThoiGianDatHang);
        txttien = dialog.findViewById(R.id.txt8);

        txtmaDH.setText(donHang_master.saleID);
        txttien.setText(UtilBasic.getNumberFormat().format(donHang_master.amount));
        txttime.setText(donHang_master.createDateTime);

        viewImg = new ArrayList<>();
        img1 = dialog.findViewById(R.id.imgstar1);
        viewImg.add(img1);
        img2 = dialog.findViewById(R.id.imgstar2);
        viewImg.add(img2);
        img3 = dialog.findViewById(R.id.imgstar3);
        viewImg.add(img3);
        img4 = dialog.findViewById(R.id.imgstar4);
        viewImg.add(img4);
        img5 = dialog.findViewById(R.id.imgstar5);
        viewImg.add(img5);
        note = dialog.findViewById(R.id.edt_note);
        dbhelp = new DataRemoteHelper(new CallBack() {
            @Override
            public void Succeed(Response e) {
                Toast.makeText(dialog.getContext(), "Thành công " + e.message(), Toast.LENGTH_SHORT).show();
                Log.e("<<succeed: ", e.message());
            }

            @Override
            public void Fail(Throwable t) {
                Toast.makeText(dialog.getContext(), "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("<<succeed: ", t.getMessage());
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rank = 1;
                changeImg();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rank = 2;
                changeImg();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rank = 3;
                changeImg();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rank = 4;
                changeImg();
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rank = 5;
                changeImg();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donHang_master.description = rank+"";

                dbhelp.updateOrder(donHang_master);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    void changeImg() {
        for (int i = 0; i < viewImg.size(); i++) {
            if (i < rank)
                viewImg.get(i).setImageResource(R.drawable.ic_star_orange_24dp);
            else viewImg.get(i).setImageResource(R.drawable.ic_star_black_24dp);
        }
    }

}