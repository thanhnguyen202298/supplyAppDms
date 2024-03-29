package com.example.sskey.dms;

import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sskey.dms.CustomerFragment.CustomerFrag;
import com.example.sskey.dms.Order.OrderList;
import com.example.sskey.dms.OrderDetail.InotifySskey;
import com.example.sskey.dms.Utils.BottomNavigationBehavior;
import com.example.sskey.dms.service.WakeServiceSSk;
import com.sskey.dms.model.BalanceSum;
import com.sskey.dms.utils.Constants;
import com.sskey.dms.utils.PreferenceUtils;

public class MainActivity extends AppCompatActivity implements OrderList.OnFragmentInteractionListener, InotifySskey {
    private String SharePreferenceNAME = "GASAPPKEYSTORE";
    private boolean doubleBackToExitPressedOnce = false;
    private ActionBar toolbar;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

//        ((BottomNavigationBehavior)layoutParams.getBehavior()).showBottomNavigationView(navigation);
        // load the store fragment by default

        //chỉnh mainactive

        //kiểm tra readkey
        //check login api
        //start ung dụng/ login
        //      Intent intent = new Intent(this, LoginActivity.class);
        //     startActivity(intent);

//        //check login exits
//        Intent intent = new Intent(this, OrderList.class);
//            startActivity(intent);

        if (PreferenceUtils.getPhone(this) != null && PreferenceUtils.getPassword(this) != null) {
            loadFragment(new CustomerFrag());
//            Intent intent = new Intent(this, DanhSachCuaHangActivity.class);
//
//            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }
        startFromDetail();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
//                    toolbar.setTitle("Khuyến mãi");
                    fragment = new CustomerFrag();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_gifts:
//                    toolbar.setTitle("Gọi tổng đài/ Mua gấp");
                    fragment = new BalanceSumActivity();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_product:
//                    toolbar.setTitle("Gọi tổng đài/ Mua gấp");
                    fragment = new SanPhamFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_cart:
//                    toolbar.setTitle("Thông tin tài khoản");
                    fragment = new TaiKhoanFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_donhang:
//                    toolbar.setTitle("Danh sách đơn hàng");
                    fragment = new OrderList();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    //<editor-fold desc="start in detail order">
    private void startFromDetail() {
        if (getIntent().hasExtra(Constants.StartFromNotify)) {
//            NotificationManager notiMaa;
//            if (Build.VERSION.SDK_INT >= 26) {
//                notiMaa = getSystemService(NotificationManager.class);
//            } else {
//                notiMaa = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            }
//            notiMaa.cancel(SSkeyNotify.Snotification.getNotifyID());
        }

        if (getIntent().hasExtra(Constants.StartFromDetail)) {
            Fragment fragment = new OrderList();
            loadFragment(fragment);
            navigation.setSelectedItemId(R.id.navigation_donhang);
        }

        WakeServiceSSk.onWWake(this);
    }

    public void setOrderMenu(){
        navigation.setSelectedItemId(R.id.navigation_donhang);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.disallowAddToBackStack();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    String CallCenter(String PhoneNumber) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + PhoneNumber));
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return "Không gọi được";
            }
            startActivity(callIntent);
            return "OK";
        } catch (Exception ex) {
            return "Không gọi được";
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true); // exist app
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void setUpNotiSsk(String notifyText) {

    }

    @Override
    public void setStyleNotiSSk() {

    }

    @Override
    public void setPendingNotiSSk(boolean noti8, String notifytext) {

    }

    @Override
    public void setActionNotiSSk(boolean noti8) {

    }

    @Override
    public int getNotifyID() {
        return 0;
    }

    @Override
    public Notification getNotification() {
        return null;
    }

    @Override
    public void showNotify() {

    }

    @Override
    public void setUpBlk() {

    }
}