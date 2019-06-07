package com.sskey.dms.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sskey.dms.R;
import com.example.sskey.dms.Utils.UtilBasic;
import com.sskey.dms.model.PriceList;

import java.util.List;

class DSCuaHangViewHolder extends RecyclerView.ViewHolder{

    public TextView txtLoai;
    public TextView txtCode;
    public TextView txtGia;

    List<PriceList> lstPrice;

    public DSCuaHangViewHolder(@NonNull View itemView) {
        super(itemView);
        txtLoai = itemView.findViewById(R.id.input_loai);
        txtCode =  itemView.findViewById(R.id.input_code);
        txtGia = itemView.findViewById(R.id.input_gia);
    }
}

public class DSCuaHangAdapter extends RecyclerView.Adapter<DSCuaHangViewHolder>{

    Context context;
    List<PriceList> lstPrice;

    public DSCuaHangAdapter(Context context, List<PriceList> lstPrice) {
        this.context = context;
        this.lstPrice = lstPrice;
    }

    @NonNull
    @Override
    public DSCuaHangViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_danhsach_cuahang, viewGroup, false);

        return new DSCuaHangViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DSCuaHangViewHolder holder, int position) {
        PriceList priceList = lstPrice.get(position);

        holder.txtLoai.setText(lstPrice.get(position).getTabGroupAll());
        if(lstPrice.get(position).getCustName()==null){
            holder.txtCode.setText(lstPrice.get(position).getCode());
        }else holder.txtCode.setText(lstPrice.get(position).getCustName());

        holder.txtGia.setText(UtilBasic.getNumberFormat().format(lstPrice.get(position).getUnitPrice()));
    }

    @Override
    public int getItemCount() {
        return lstPrice.size();
    }
    public void updatePriceList(List<PriceList> price) {
        lstPrice = price;
        notifyDataSetChanged();
    }
}