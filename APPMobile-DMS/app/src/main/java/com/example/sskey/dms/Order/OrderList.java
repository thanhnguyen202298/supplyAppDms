package com.example.sskey.dms.Order;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sskey.dms.R;
import com.example.sskey.dms.Utils.UtilBasic;
import com.google.gson.Gson;
import com.sskey.dms.data.remote.APIUtils;
import com.sskey.dms.data.remote.DataRemoteHelper;
import com.sskey.dms.data.remote.SOService;
import com.sskey.dms.model.Customer;
import com.sskey.dms.model.Order;
import com.sskey.dms.model.UserInFoDTO;
import com.sskey.dms.utils.Constants;

import java.util.List;

import retrofit2.Response;

public class OrderList extends Fragment implements DataRemoteHelper.CallListOrder, AdapterRC.TakeDetail {

    private RecyclerView myRecycleView;

    private AdapterRC donhangAda;
    private DataRemoteHelper dbHelper;
    private UserInFoDTO user;
    private Context acty;
    private boolean more;
    private boolean firrst;
    private boolean filter;
    private boolean resumeState;
    private String serkk;
    private Button btnseak;
    private EditText edtseak;

    private SOService vservice;
    ProgressDialog progressDialog;

    private void initialized() {

        btnseak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donhangAda.filter(edtseak.getText().toString());
            }
        });

        donhangAda = new AdapterRC(this.getContext(), this);
        LinearLayoutManager lm = new LinearLayoutManager(this.getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        onScrollCallBack.setLayout(lm);

        myRecycleView.setLayoutManager(lm);
        myRecycleView.setAdapter(donhangAda);
        myRecycleView.setHasFixedSize(true);
        myRecycleView.setOnScrollListener(onScrollCallBack);

        more = true;
        filter = false;

        dbHelper = new DataRemoteHelper(this);
        vservice = APIUtils.getSOService();
        Gson gson = new Gson();
        user = UtilBasic.getUser(getContext());

        progressDialog = new ProgressDialog(getContext(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Wait....");
    }

    private void load(int page) {
        if (filter) return;
        if (mParam1 != null) serkk = mParam1;
        serkInLoad(page, serkk);
    }

    private void serkInLoad(int page, String status) {
        if (status != null)
            status = (status.equals(Constants.inallOrder)) ? "" : status;
        if (page < 2 || filter)
            donhangAda.getMylist().clear();
        dbHelper.getOrderList(page, user.getEmployeeId(), status);
    }

    @Override
    public void Succeed(Response e) {
        try {
            List<Order> vm = (List<Order>) e.body();
            if (vm.size() < 15) {
                more = false;
                if (firrst) {
                    if (vm.size() < 1)
                        Toast.makeText(acty, "không có dữ liệu hiển thị", Toast.LENGTH_LONG).show();
                }
            }
            if (filter) {
                donhangAda.getMylist().clear();
                filter = false;
            }

            donhangAda.updateList(vm);
            progressDialog.cancel();

        } catch (Exception ex) {
            Toast.makeText(acty, "Lỗi data \n" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void Fail(Throwable t) {
        progressDialog.cancel();
        Toast.makeText(acty, "Lỗi kết nối \n" + t.getMessage(), Toast.LENGTH_LONG).show();
    }

    OnScrollCallBack onScrollCallBack = new OnScrollCallBack(15) {
        @Override
        public Boolean loadMore(int page, int totalItems, RecyclerView v) {
            if (more)
                load(page);
            return true;
        }
    };

    @Override
    public void StartDetail(Order pos) {
        //Start detail

        String objstr = UtilBasic.ObjectToJson(pos);
        Intent it = new Intent(acty, OrderDetailActivity.class);
        it.putExtra(Constants.OrderObj, objstr);
        it.putExtra(Constants.OrderRequest, Constants.OldOrder);
        startActivity(it);
    }

    //Fragment

    private OnFragmentInteractionListener mListener;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static String mParam1;
    static String mParam2;

    public OrderList() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static OrderList newInstance(String param1, String param2) {
        OrderList fragment = new OrderList();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_danhsach_donhang, container, false);
        myRecycleView = v.findViewById(R.id.donhangList);
        btnseak = v.findViewById(R.id.btnsearch);
        edtseak = v.findViewById(R.id.editText);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        acty = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        initialized();
        load(1);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
