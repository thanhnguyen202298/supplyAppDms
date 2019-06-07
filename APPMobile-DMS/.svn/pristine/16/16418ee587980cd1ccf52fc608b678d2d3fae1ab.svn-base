package com.sskey.dms.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.sskey.dms.R;
import com.example.sskey.dms.Utils.UtilBasic;
import com.sskey.dms.model.SaleLine;
import com.sskey.dms.model.SanPham;
import com.sskey.dms.utils.UpdateBadgeCart;

import java.util.HashMap;
import java.util.List;

class SanPhamRecycleViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgSP;
    public TextView txtTenSP;
    public TextView txtGiaSP;
    public ElegantNumberButton enbSoLuongSp;
    List<SanPham> lstsanPham;

//    NotificationBadge mBadge;

    public SanPhamRecycleViewHolder(View itemView) {

        super(itemView);
        imgSP = (ImageView) itemView.findViewById(R.id.imageSanPham);
        txtTenSP = (TextView) itemView.findViewById(R.id.input_name);
        txtGiaSP = (TextView) itemView.findViewById(R.id.input_gia);
        enbSoLuongSp = (ElegantNumberButton) itemView.findViewById(R.id.number_soluong);
//        mBadge = (NotificationBadge) itemView.findViewById(R.id.badge);

    }
}

public class SanPhamRecycleAdapter extends RecyclerView.Adapter<SanPhamRecycleViewHolder> {

    Context context;
    List<SanPham> lstsanPham;
    HashMap<Integer, SaleLine> lstDHDT;
    SaleLine donHang_detail;
    private UpdateBadgeCart updateBadgeCart;
    int sumMaster = 0;


    public SanPhamRecycleAdapter(Context context, List<SanPham> lstsanPham, UpdateBadgeCart updateBadgeCart) {
        this.context = context;
        this.lstsanPham = lstsanPham;
        this.updateBadgeCart = updateBadgeCart;

        lstDHDT = new HashMap<Integer, SaleLine>();
//        donHang_detail = new DonHang_Detail();
    }

    public void setLstsanPham(List<SanPham> lstsanPham) {
        this.lstsanPham = lstsanPham;
        notifyDataSetChanged();
    }

    @Override
    public SanPhamRecycleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_sanpham, viewGroup, false);

        return new SanPhamRecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SanPhamRecycleViewHolder holder, final int position) {

//        Glide.with(context).load(movieList.get(position).getImageUrl()).apply(RequestOptions.centerCropTransform()).into(holder.image);
        SanPham sanPham = lstsanPham.get(position);
        holder.txtTenSP.setText(lstsanPham.get(position).getItemName());
        holder.txtGiaSP.setText(UtilBasic.getNumberFormat().format(lstsanPham.get(position).UnitPrice));
        holder.enbSoLuongSp.setNumber("0");

        if(lstsanPham.get(position).getColor()!=null) {
            if (lstsanPham.get(position).getColor().equalsIgnoreCase("do")) {
                holder.imgSP.setImageResource(R.drawable.bgdo);
            } else if (lstsanPham.get(position).getColor().equalsIgnoreCase("HONG")) {
                holder.imgSP.setImageResource(R.drawable.bghong);
            } else if (lstsanPham.get(position).getColor().equalsIgnoreCase("VANG")) {
                holder.imgSP.setImageResource(R.drawable.bgvang);
            } else if (lstsanPham.get(position).getColor().equalsIgnoreCase("XAM")) {
                holder.imgSP.setImageResource(R.drawable.bgxam);
            } else if (lstsanPham.get(position).getColor().equalsIgnoreCase("XANHDUONG")) {
                holder.imgSP.setImageResource(R.drawable.bg);
            } else if (lstsanPham.get(position).getColor().equalsIgnoreCase("XANHLA")) {
                holder.imgSP.setImageResource(R.drawable.bgxanh);
            }
        }
        holder.enbSoLuongSp.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                donHang_detail = new SaleLine();
                int num = Integer.parseInt(holder.enbSoLuongSp.getNumber());

                if (lstDHDT.containsKey(position)) {
                    sumMaster += num - lstDHDT.get(position).quantity;
                } else {
                    sumMaster += num;
                }
                updateBadgeCart.updateBadge(sumMaster);
//                Toast.makeText(context, position + " " + sumMaster + "", Toast.LENGTH_SHORT).show();

                if (num > 0) {

                    lstDHDT.put(position, null);
                    donHang_detail.quantity = num;
                    donHang_detail.itemID = lstsanPham.get(position).getItemID();
                    donHang_detail.itemName = lstsanPham.get(position).getItemName();
                    donHang_detail.salePrice = lstsanPham.get(position).UnitPrice;
                    donHang_detail.itemColor = lstsanPham.get(position).getColor();
                    lstDHDT.put(position, donHang_detail);
                } else {
                    donHang_detail.quantity = 0;
                    lstDHDT.remove(position);
//                    updateBadgeCart.updateBadge(lstDHDT.size());
                }
            }

        });
    }

    public HashMap getCart() {
        return lstDHDT;
    }

    @Override
    public int getItemCount() {
        return lstsanPham.size();
    }

    public void updateSanPham(List<SanPham> sanPham) {
        lstsanPham = sanPham;
        notifyDataSetChanged();
    }

    public SanPham getSanPham(int adapterPosition) {
        return lstsanPham.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }
}
