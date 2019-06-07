package com.example.sskey.dms;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

//    Fragment fragment = null;
//    FragmentTransaction fragmentTransaction;
//
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.navigation_trangchu:
//                    fragment = new KhuyenMaiFragment();
//                    switchFragment(fragment);
//                    return true;
////                    case R.id.navigation_dashboard:
////                        fragment = new FragmentDashBoard();
////                        switchFragment(fragment);
////                        return true;
////                    case R.id.navigation_notifications:
////                        fragment = new FragmentNotification();
////                        switchFragment(fragment);
////                        return true;
//            }
//            return false;
//        }
//    };
//
//    private void switchFragment(Fragment fragment) {
//        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.navigation, fragment);
//        fragmentTransaction.commit();
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        navigation.setSelectedItemId(R.id.navigation_trangchu);
//    }
}
