package com.sskey.dms.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sskey.dms.DanhSachCuaHangActivity;
import com.example.sskey.dms.R;
import com.example.sskey.dms.Utils.UtilBasic;
import com.sskey.dms.model.SanPham;
import com.sskey.dms.utils.UpdateBadgeCart;

import java.util.List;

class SanPhamFragmentViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgSP;
    public TextView txtTenSP;
    public TextView txtGiaSP;
    public TextView enbSoLuongSp;


    public SanPhamFragmentViewHolder(View itemView) {

        super(itemView);
        imgSP = itemView.findViewById(R.id.imageSanPham);
        txtTenSP = itemView.findViewById(R.id.input_name);
        txtGiaSP =  itemView.findViewById(R.id.input_gia);
        enbSoLuongSp = itemView.findViewById(R.id.number_soluong);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
public class SanPhamFragmentAdapter extends RecyclerView.Adapter<SanPhamFragmentViewHolder>{

    Context context;
    List<SanPham> lstsanPham;
    private UpdateBadgeCart updateBadgeCart;

    public SanPhamFragmentAdapter(Context context, List<SanPham> lstsanPham, UpdateBadgeCart updateBadgeCart) {
        this.context = context;
        this.lstsanPham = lstsanPham;
        this.updateBadgeCart = updateBadgeCart;

    }

    @NonNull
    @Override
    public SanPhamFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_fragment_sanpham, viewGroup, false);

        return new SanPhamFragmentViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull SanPhamFragmentViewHolder holder, int position) {
        final String id = lstsanPham.get(position).getItemID();

        final String nameItem = lstsanPham.get(position).getItemName();
        holder.txtTenSP.setText(lstsanPham.get(position).getItemName());
        holder.txtGiaSP.setText(UtilBasic.getNumberFormat().format(lstsanPham.get(position).getUnitPrice()));
        holder.enbSoLuongSp.setText(UtilBasic.getNumberFormat().format(lstsanPham.get(position).getOnhand()));

        if(lstsanPham.get(position).getColor()!=null) {
            if (lstsanPham.get(position).getColor().equalsIgnoreCase("đỏ")) {
                holder.imgSP.setImageResource(R.drawable.bgdo);
            } else if (lstsanPham.get(position).getColor().equalsIgnoreCase("hồng")) {
                holder.imgSP.setImageResource(R.drawable.bghong);
            } else if (lstsanPham.get(position).getColor().equalsIgnoreCase("vàng")) {
                holder.imgSP.setImageResource(R.drawable.bgvang);
            } else if (lstsanPham.get(position).getColor().equalsIgnoreCase("xám")) {
                holder.imgSP.setImageResource(R.drawable.bgxam);
            } else if (lstsanPham.get(position).getColor().equalsIgnoreCase("xanh dương")) {
                holder.imgSP.setImageResource(R.drawable.bg);
            } else if (lstsanPham.get(position).getColor().equalsIgnoreCase("xanh")) {
                holder.imgSP.setImageResource(R.drawable.bgxanh);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachCuaHangActivity.class);
                intent.putExtra("IDITEM", id);
                intent.putExtra("NAMEITEM", nameItem);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lstsanPham.size();
    }

    public void updateSanPham(List<SanPham> sanPham) {
        lstsanPham = sanPham;
        notifyDataSetChanged();
    }
}
