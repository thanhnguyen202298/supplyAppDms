package com.sskey.dms.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SanPham {

    @SerializedName("ItemID")
    private String ItemID;
    @SerializedName("ItemName")
    private String ItemName;
    @SerializedName("Volume")
    private double Volume;
    @SerializedName("Color")
    private String Color;
    @SerializedName("Image")
    private String Image;

    private int Onhand;

    public double UnitPrice;

    public SanPham(String itemID, String itemName, double volume, String color, String image, int onhand, double unitPrice, List<SaleLine> donHangChiTiets) {
        ItemID = itemID;
        ItemName = itemName;
        Volume = volume;
        Color = color;
        Image = image;
        Onhand = onhand;
        UnitPrice = unitPrice;
        DonHangChiTiets = donHangChiTiets;
    }

    List<SaleLine> DonHangChiTiets;

    public SanPham() {

    }

    public int getOnhand() {
        return Onhand;
    }

    public void setOnhand(int onhand) {
        Onhand = onhand;
    }

    public List<SaleLine> getDonHangChiTiets() {
        return DonHangChiTiets;
    }

    public void setDonHangChiTiets(List<SaleLine> donHangChiTiets) {
        DonHangChiTiets = donHangChiTiets;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public double getVolume() {
        return Volume;
    }

    public void setVolume(double volume) {
        Volume = volume;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "ItemID='" + ItemID + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", Volume=" + Volume +
                ", Color='" + Color + '\'' +
                ", Image='" + Image + '\'' +
                ", Onhand=" + Onhand +
                ", UnitPrice=" + UnitPrice +
                ", DonHangChiTiets=" + DonHangChiTiets +
                '}';
    }
}
