package com.sskey.dms.model;

public class EmployeeDTO {
    private String EmployeeId;
    private String EmployeeName;
    private String Address;
    private String Tell;
    private String VehicleDefault;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String employeeId, String employeeName, String address, String tell, String vehicleDefault) {
        EmployeeId = employeeId;
        EmployeeName = employeeName;
        Address = address;
        Tell = tell;
        VehicleDefault = vehicleDefault;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTell() {
        return Tell;
    }

    public void setTell(String tell) {
        Tell = tell;
    }

    public String getVehicleDefault() {
        return VehicleDefault;
    }

    public void setVehicleDefault(String vehicleDefault) {
        VehicleDefault = vehicleDefault;
    }
}
