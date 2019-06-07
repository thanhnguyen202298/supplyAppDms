package com.example.sskey.dms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.goodiebag.pinview.Pinview;
import com.sskey.dms.utils.PreferenceUtils;

public class PinViewActivity extends AppCompatActivity {
    Pinview pvNhapMa;
    AppCompatButton btnNhapMa;
    TextView txtSDTKhach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_view);

        //init view
        pvNhapMa = (Pinview) findViewById(R.id.pv_NhapMa);
        btnNhapMa = (AppCompatButton) findViewById(R.id.btn_nhapMa);
        txtSDTKhach = (TextView) findViewById(R.id.sdt_Khach);

        final String obj = getIntent().getStringExtra("NGUOIDUNG");
        txtSDTKhach.setText(getIntent().getStringExtra("SDT"));
        final String matkhau = getIntent().getStringExtra("MATKHAU");
        //check null
        if (matkhau != null) {
            pvNhapMa.setValue(matkhau);
        } else {
            pvNhapMa.setValue("0000");
        }

        final String phone = getIntent().getStringExtra("SDT");

        btnNhapMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login callback
                // save in Preference
                PreferenceUtils.saveObj(obj, PinViewActivity.this);
                PreferenceUtils.savePassword(matkhau, PinViewActivity.this);
                PreferenceUtils.savePhone(phone, PinViewActivity.this);
                Intent accountsIntent = new Intent(PinViewActivity.this, MainActivity.class);
                startActivity(accountsIntent);
//                accountsIntent.putExtra("SDT", PreferenceUtils.getPhone(PinViewActivity.this) );
                finish();

            }
        });

    }
}
