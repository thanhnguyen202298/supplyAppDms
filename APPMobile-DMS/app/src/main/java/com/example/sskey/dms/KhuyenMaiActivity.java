package com.example.sskey.dms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sskey.dms.utils.PreferenceUtils;

public class KhuyenMaiActivity extends AppCompatActivity {
    private TextView textPhone;
    private Button btnMuaHang;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khuyen_mai);

        textPhone = (TextView) findViewById(R.id.phoneCustomer);
        btnMuaHang = (Button) findViewById(R.id.btn_muaHang);
        final Intent intent = getIntent();
        if (intent.hasExtra("EMAIL")){
            String nameFromIntent = getIntent().getStringExtra("SDT");
            textPhone.setText("Welcome " + nameFromIntent);
        }else{
            String SDT = PreferenceUtils.getPhone(this);
            textPhone.setText("Welcome " + SDT);

        }

        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMuaHang = new Intent(KhuyenMaiActivity.this, SanPhamActivity.class);
                startActivity(intentMuaHang);
//                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                PreferenceUtils.savePassword(null, this);
                PreferenceUtils.savePhone(null, this);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
