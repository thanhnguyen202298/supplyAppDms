package com.example.sskey.dms.Order;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sskey.dms.Utils.CommonStatus;
import com.example.sskey.dms.Utils.UtilBasic;
import com.example.sskey.dms.R;
import com.sskey.dms.model.Customer;
import com.sskey.dms.model.Order;

import java.util.ArrayList;
import java.util.List;

public class AdapterRC extends RecyclerView.Adapter<AdapterRC.DonHangListVH> {
    private List<Order> mylist;
    private Context ctx;
    private CommonStatus st;
    private List<Order> tmp;

    private TakeDetail takeDetail;

    public interface ClickItemCallback {
        void clickItem();
    }

    public AdapterRC(Context context, TakeDetail todetail) {
        mylist = new ArrayList<>();
        tmp = new ArrayList<>();
        ctx = context;
        takeDetail = todetail;
    }

    @NonNull
    @Override
    public DonHangListVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater lif = LayoutInflater.from(viewGroup.getContext());
        View v = lif.inflate(R.layout.item_danhsach_donhang, viewGroup, false);

        DonHangListVH vh = new DonHangListVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final DonHangListVH donHangListVH, final int i) {

        donHangListVH.txtTimeBook.setText(mylist.get(i).createDateTime);
        donHangListVH.txtTimeDeliver.setText(mylist.get(i).deliTime);

        if (mylist.get(i).shipTo != null)
            donHangListVH.txtAddress.setText(mylist.get(i).shipTo);
        donHangListVH.txtOrderCode.setText(mylist.get(i).lotId + "");

        donHangListVH.txtSumAmout.setText(UtilBasic.getNumberFormat().format(mylist.get(i).amount));

        st = UtilBasic.getStatusOrder(mylist.get(i).saleStatus, ctx);
        donHangListVH.txtStatus.setText(mylist.get(i).saleStatus);
        donHangListVH.txtStatus.setTextColor(st.MyColor);
//        donHangListVH.cbSpeed.setChecked(mylist.get(i).loaiYeuCau>0);

        donHangListVH.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeDetail.StartDetail(mylist.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public void updateList(List<Order> mUpdate) {
        mylist.addAll(mUpdate);
        tmp = mylist;
        notifyDataSetChanged();
    }

    public void filter(String filtertext) {
        if(filtertext.equals("")||filtertext ==null)
        {
            mylist = tmp;
            notifyDataSetChanged();
            return;
        }
        List<Order> filterlissst = new ArrayList<>();
        for (Order i : mylist) {
            if (i.shopName.toLowerCase().contains(filtertext) || i.shipTo.toLowerCase().contains(filtertext) || i.shopPhone.toLowerCase().contains(filtertext))
                filterlissst.add(i);
        }

        if (filterlissst.size() > 0) {
            mylist = filterlissst;
            notifyDataSetChanged();
        }else Toast.makeText(ctx, "nội dung cần tìm không có", Toast.LENGTH_SHORT).show();
    }

    public List<Order> getMylist() {
        return mylist;
    }

    public class DonHangListVH extends RecyclerView.ViewHolder {

        public TextView txtOrderCode;
        public TextView txtTimeBook;
        public TextView txtTimeDeliver;
        public TextView txtSumAmout;
        public TextView txtAddress;
        public TextView txtStatus;
        public CheckBox cbSpeed;
        public AppCompatImageView image;

        public DonHangListVH(View view) {
            super(view);

            txtOrderCode = view.findViewById(R.id.txt_MaDonHang);
            txtTimeBook = view.findViewById(R.id.txtThoiGianDatHang);
            txtTimeDeliver = view.findViewById(R.id.txt_TrangThaiThoiGian);
            txtAddress = view.findViewById(R.id.txtDiaChiGiaoHang);
            txtSumAmout = view.findViewById(R.id.txt8);
            txtStatus = view.findViewById(R.id.textView8);
            cbSpeed = view.findViewById(R.id.chk_Gap);
            image = view.findViewById(R.id.apImage);
            cbSpeed.setEnabled(false);
        }
    }

    public interface TakeDetail {
        void StartDetail(Order postIt);
    }
}
