package com.sskey.dms.model;

import java.util.List;

public class Customer {
    public String CustAccount;
    public String CustGroup;
    public String CustName;
    public String Address;
    public String Tell;
    public String SaleMan;

    public List<Order> SaleOrders;
    public List<PriceList> PriceLists;
    public boolean evt_click = false;
    public boolean isVisi = true;
}
