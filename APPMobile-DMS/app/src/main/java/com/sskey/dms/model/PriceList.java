package com.sskey.dms.model;

public class PriceList {

    private String ID;
    private String TabGroupAll;
    private String Code;
    private String ItemID;
    private double UnitPrice;
    private String FromDate;
    private String ToDate;

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    private String CustName;

    public PriceList() {
    }

    public PriceList(String ID, String tabGroupAll, String code, String itemID, double unitPrice, String fromDate, String toDate) {
        this.ID = ID;
        TabGroupAll = tabGroupAll;
        Code = code;
        ItemID = itemID;
        UnitPrice = unitPrice;
        FromDate = fromDate;
        ToDate = toDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTabGroupAll() {
        return TabGroupAll;
    }

    public void setTabGroupAll(String tabGroupAll) {
        TabGroupAll = tabGroupAll;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }
}
