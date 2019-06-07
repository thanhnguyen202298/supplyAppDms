package com.example.sskey.dms;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sskey.dms.Order.OnScrollCallBack;
import com.sskey.dms.adapter.SanPhamFragmentAdapter;
import com.sskey.dms.data.remote.APIUtils;
import com.sskey.dms.data.remote.SOService;
import com.sskey.dms.model.SanPham;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SanPhamFragment extends Fragment {

    List<SanPham> sanPhamList;
    SOService mService;
    RecyclerView recyclerViewSP;
    SanPhamFragmentAdapter recycleAdapter;

    private OnFragmentInteractionListener mListener;
    private boolean more;
    private int page = 1;

    public SanPhamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_san_pham, container, false);

        //init data
        sanPhamList = new ArrayList<>();

        //init view
        mService = APIUtils.getSOService();
        recyclerViewSP = v.findViewById(R.id.rv_sanpham);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        onScrollCallBack.setLayout(layoutManager);

        recyclerViewSP.setLayoutManager(layoutManager);
        recycleAdapter = new SanPhamFragmentAdapter(getActivity(), sanPhamList, null);
        recyclerViewSP.setAdapter(recycleAdapter);
        recyclerViewSP.setHasFixedSize(true);
//        recyclerViewSP.setOnScrollListener(onScrollCallBack);
        loadSanPham(page);

//        more = true;
//        filter = false;
        return v;
    }

    private void loadSanPham(int page) {
//
        mService.getAllProduct(page).enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response.body() != null) {
                    sanPhamList = response.body();
                    recycleAdapter.updateSanPham(sanPhamList);
//                    StyleableToast.makeText(getActivity(), "Danh sách sản phẩm", R.style.errorserverToast).show();
                }
// else {
//                    sanPhamList = new ArrayList<>();
//                    sanPhamList.add(new SanPham("1223", "do", 0, "DO", null, 10, 2000, null));
//                    sanPhamList.add(new SanPham("1223", "vo", 0, "VANG", null, 10, 2000, null));
//                    sanPhamList.add(new SanPham("1223", "co", 0, "HONG", null, 10, 2000, null));
//                    sanPhamList.add(new SanPham("1223", "co", 0, "HONG", null, 10, 2000, null));
//                    sanPhamList.add(new SanPham("1223", "co", 0, "VANG", null, 10, 2000, null));
//                    sanPhamList.add(new SanPham("1223", "co", 0, "DO", null, 10, 2000, null));
//                    sanPhamList.add(new SanPham("1223", "co", 0, "DO", null, 10, 2000, null));
//
//                    recycleAdapter.updateSanPham(sanPhamList);
////                    StyleableToast.makeText(getActivity(), "Đây là danh sách sản phẩm thử", R.style.errorserverToast).show();
//                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

//    OnScrollCallBack onScrollCallBack = new OnScrollCallBack(15) {
//        @Override
//        public Boolean loadMore(int page, int totalItems, RecyclerView v) {
//            if (more)
//                loadSanPham(page);
//            return true;
//        }
//    };

    @Override
    public void onResume() {
        super.onResume();
//        loadSanPham(1);
    }
}
