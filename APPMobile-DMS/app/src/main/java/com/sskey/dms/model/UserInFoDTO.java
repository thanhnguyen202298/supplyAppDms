package com.sskey.dms.model;

public class UserInFoDTO {
    private String UserID;
    private String UserName;
    private String PassWord;
    private String EmployeeId;
    private String Role;
    private EmployeeDTO Employee;

    public UserInFoDTO() {
    }

    public UserInFoDTO(String userID, String userName, String passWord, String employeeId, String role, EmployeeDTO employee) {
        UserID = userID;
        UserName = userName;
        PassWord = passWord;
        EmployeeId = employeeId;
        Role = role;
        Employee = employee;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public EmployeeDTO getEmployee() {
        return Employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        Employee = employee;
    }
}
