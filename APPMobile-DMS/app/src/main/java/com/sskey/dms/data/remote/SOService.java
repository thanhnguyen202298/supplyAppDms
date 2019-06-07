package com.sskey.dms.data.remote;

import com.sskey.dms.model.BalanceSum;
import com.sskey.dms.model.Customer;
import com.sskey.dms.model.Order;
import com.sskey.dms.model.PriceList;
import com.sskey.dms.model.SaleLine;
import com.sskey.dms.model.SanPham;
import com.sskey.dms.model.StatusOrder;
import com.sskey.dms.model.UserInFoDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface SOService {

    //Get

    //Customer
    @GET("/customer/getlist")
    Call<List<Customer>> getCustomerList(@Query("page") int page);

    @GET("/customer/getListOwner")
    Call<List<Customer>> getCustomerBySale(@Query("page") int page, @Query("saleid") String saleid);

    @GET("/customer/getItemById")
    Call<Customer> getCustomerById(@Query("id") String id);

    @POST("/customer/save")
    @FormUrlEncoded
    Call<Customer> saveCustomer(@Body Customer customer);

    //Employee
    @GET("/Employee/getlist")
    Call<List<Customer>> getEmployeeList(@Query("page") int page);

    @POST("/Employee/save")
    @FormUrlEncoded
    Call<Customer> saveEmployee(@Body Customer customer);

    @PUT("/Employee/update")
    Call<SucceedUpdate> updateEmployee(@Body Customer kh);


    //Order
    @POST("/SaleOrder/save")
    Call<Integer> saveOrder(@Body Order saleOrder);

    @GET("/SaleOrder/getlist")
    Call<List<Order>> getOrderList(@Query("page") int page, @Query("man") String saleman, @Query("cust") String status);

    @GET("/SaleOrder/getOrderById")
    Call<Order> getOrderById(@Query("id") String id);

    @PUT("/SaleOrder/update")
    Call<SucceedUpdate> update(@Body Order order);

    //get list by shop
    @GET("/SaleOrder/getDetails")
    Call<List<SaleLine>> getDetails(@Query("saleid") String saleid);

    // Filter fBy Notify Order
    //
    //

    @GET("/SaleOrder/getNotifyByShop")
    Call<List<Order>> getNotifyByShop(@Query("shop") String usncode);

    @GET("/SaleOrder/getNotifyByUser")
    Call<List<Order>> getNotifyByUser(@Query("usn") String usncode);

    //
    @GET("/SaleOrder/getNotifyByPostMan")
    Call<List<Order>> getNotifyByPostMan(@Query("postman") String postman);

    //
    //
    //End Filter Notify Order


    //processing
    @GET("/sanpham/getlist")
    Call<List<SanPham>> getProductList();

    //get By Id


    @GET("/sanpham/getsp/")
    Call<SanPham> getProductById(@Query("id") String ProductCode);

    //Products

    @POST("/SanPham/savesp")
    @FormUrlEncoded
    Call<SanPham> saveProduct(@Body SanPham sanPham);

    @PUT("/sanpham/updatesp")
    @FormUrlEncoded
    Call<SucceedUpdate> updateProduct(@Body SanPham sanPham);

    //login(string usn, string pwd): đăng nhập
    //	save(SPData.UserInfo userInfo) lưu mới thông tin người dùng (thông tin đăng nhập của nhân viên)
    //	update(SPData.UserInfo userInfo): cập nhật thông tin đăng nhập


    @POST("/UsInfo/login")
    @FormUrlEncoded
    Call<UserInFoDTO> login(@Field("usn") String usn, @Field("pwd") String pas);

    @POST("/UsInfo/getUsById")
    @FormUrlEncoded
    Call<UserInFoDTO> getUsInfoById(@Field("id") String id);

    @PUT("/UsInfo/update")
    Call<SucceedUpdate> updateCustomer(@Body UserInFoDTO kh);

// Inventory
//    getList(int page): danh sách item trong kho
//    getItemById(string id): lấy item theo id
//    save(SPData.Inventory inventory): lưu mới item vào kho
//    update(SPData.Inventory inventory): update thông tin item trong kho

//    @GET("/Inventory/getlist")
//    Call<List<SanPham>> getProduct(@Query("page") int page, @Query("customercode") String customercode, @Query("custgroup") String custgroup);

    @GET("/Inventory/getlist")
    Call<List<SanPham>> getProductList(@Query("page") int page, @Query("customercode") String customercode, @Query("custgroup") String custgroup);

    @GET("/Inventory/getAllProduct")
    Call<List<SanPham>> getAllProduct(@Query("page") int page);

    //balance sum
    @GET("/AddBallan/getBallanBySaleToday")
    Call<BalanceSum> getListBySaleDate(@Query("page") int page, @Query("SaleID") String SaleID, @Query("fromdate") String todate);

    @PUT("/AddBallan/syncBallancing")
    Call<BalanceSum> syncBallancing(@Body BalanceSum bs);

    @POST("/AddBallan/save")
    @FormUrlEncoded
    Call<Integer> saveBS(@Body BalanceSum sum);

    //price list
    @GET("/Inventory/getItemByID")
    Call<List<PriceList>> getPriceList(@Query("id") String idItem);

}