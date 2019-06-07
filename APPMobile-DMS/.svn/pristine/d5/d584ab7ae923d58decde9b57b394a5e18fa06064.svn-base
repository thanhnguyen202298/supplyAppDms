package com.example.sskey.dms.CustomerFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sskey.dms.R;
import com.sskey.dms.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRCadapter extends RecyclerView.Adapter<CustomerRCadapter.ViewCustomerHolder> {

    private List<Customer> customers;
    private List<Customer> tmp;
    private Context ctx;
    private int oldpos;
    private CustomerClickCb myclik;

    public CustomerRCadapter(CustomerClickCb callcustomer) {
        customers = new ArrayList<>();
        tmp = new ArrayList<>();
        oldpos = -1;
        myclik = callcustomer;
    }

    @NonNull
    @Override
    public ViewCustomerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ctx = viewGroup.getContext();
        LayoutInflater lin = LayoutInflater.from(ctx);
        View v = lin.inflate(R.layout.item_customer, viewGroup, false);
        ViewCustomerHolder vh = new ViewCustomerHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewCustomerHolder viewCustomerHolder, final int i) {
        viewCustomerHolder.custName.setText(customers.get(i).CustName);
        viewCustomerHolder.custAddres.setText(customers.get(i).Address);
        viewCustomerHolder.custTel.setText(customers.get(i).Tell);


        setShowBtn(customers.get(i).evt_click, viewCustomerHolder.OrderView);
        setShowBtn(customers.get(i).evt_click, viewCustomerHolder.SaleStart);
        setShowBtn(customers.get(i).isVisi, viewCustomerHolder.itemView);

//<editor-fold desc="show button" collapse="default">
//        viewCustomerHolder.mylay.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (oldpos >= 0) customers.get(oldpos).evt_click = false;
//                customers.get(i).evt_click = !customers.get(i).evt_click;
////                setShowBtn(customers.get(i).evt_click, viewCustomerHolder.OrderView);
////                setShowBtn(customers.get(i).evt_click, viewCustomerHolder.SaleStart);
//                notifyDataSetChanged();
//                oldpos = i;
//                return customers.get(i).evt_click;
//            }
//        });
        //</editor-fold>

        viewCustomerHolder.mylay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldpos >= 0) customers.get(oldpos).evt_click = false;
                customers.get(i).evt_click = !customers.get(i).evt_click;
                notifyDataSetChanged();
                oldpos = i;
            }
        });

        viewCustomerHolder.OrderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myclik.viewOrder(customers.get(i).CustAccount);
            }
        });

        viewCustomerHolder.SaleStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myclik.gotoSale(customers.get(i));
            }
        });

    }

    private void setShowBtn(boolean showBtn, View view) {
        if (showBtn) view.setVisibility(View.VISIBLE);
        else {
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public void cleaer() {
        customers.clear();
    }

    public void update(List<Customer> customerList) {
        customers.addAll(customerList);
        tmp = customers;
        notifyDataSetChanged();
    }

    public void filter(String filtertext) {
        if(filtertext.equals("")||filtertext ==null)
        {
            customers = tmp;
            notifyDataSetChanged();
            return;
        }
        List<Customer> filterlissst = new ArrayList<>();
        for (Customer i : customers) {
            if (i.CustName.toLowerCase().contains(filtertext) || i.Address.toLowerCase().contains(filtertext) || i.Tell.toLowerCase().contains(filtertext))
                filterlissst.add(i);
        }

        if (filterlissst.size() > 0) {
            customers = filterlissst;
            notifyDataSetChanged();
        }else Toast.makeText(ctx, "từ cần tìm không có", Toast.LENGTH_SHORT).show();
    }


    public final static class ViewCustomerHolder extends RecyclerView.ViewHolder {
        public TextView custName;
        public TextView custAddres;
        public TextView custTel;
        public Button SaleStart;
        public Button OrderView;
        public ConstraintLayout mylay;

        public ViewCustomerHolder(View v) {
            super(v);
            custName = v.findViewById(R.id.cust_name);
            custAddres = v.findViewById(R.id.cust_address);
            custTel = v.findViewById(R.id.cust_tell);

            SaleStart = v.findViewById(R.id.btnSale);
            OrderView = v.findViewById(R.id.btnViewOrder);
            mylay = v.findViewById(R.id.mylayout);
        }
    }

    public interface CustomerClickCb {
        void gotoSale(Customer customer);

        void viewOrder(String custId);
    }
}
