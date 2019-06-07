package com.example.sskey.dms;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.sskey.dms.utils.PreferenceUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link KhuyenMaiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhuyenMaiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView textPhone;
    private Button btnMuaHang;
    private FloatingActionButton flbCall;
    String phone = "0281080";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public KhuyenMaiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KhuyenMaiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KhuyenMaiFragment newInstance(String param1, String param2) {
        KhuyenMaiFragment fragment = new KhuyenMaiFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
//        Intent intent = getIntent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_khuyen_mai, container, false);

        textPhone = (TextView) view.findViewById(R.id.phoneCustomerFM);
        btnMuaHang = (Button) view.findViewById(R.id.btn_muaHangFM);
        flbCall = (FloatingActionButton) view.findViewById(R.id.fabCall) ;



        String SDT = PreferenceUtils.getPhone(view.getContext());
        textPhone.setText("Xin chào: " + SDT);


        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });


        flbCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fabCall();
                CallCenter(phone);
            }
        });

        return view;

    }

    public void updateDetail() {
        Intent intent = new Intent(getActivity(), SanPhamActivity.class);
        startActivity(intent);
    }

    public void fabCall(){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel://" + phone)));
    }
    // Inflate the layout for this fragment


    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.log_out:
                PreferenceUtils.savePassword(null, getActivity());
                PreferenceUtils.savePhone(null, getActivity());
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    String CallCenter(String PhoneNumber) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + PhoneNumber));
            if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                return "Không gọi được";
            }
            startActivity(callIntent);
            return "OK";
        } catch (Exception ex) {
            return "Không gọi được";
        }

    }
}
