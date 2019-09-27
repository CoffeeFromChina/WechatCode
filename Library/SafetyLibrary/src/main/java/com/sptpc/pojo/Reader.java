package com.sptpc.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

//借书证办理的读者信息
public class Reader extends BaseRowModel implements Serializable {
    @ExcelProperty(value = "用户名", index = 0)
    private String rdID;        //用户名

    @ExcelProperty(value = "姓名", index = 1)
    private String rdName;      //姓名

    @ExcelProperty(value = "性别", index = 2)
    private String rdSex;       //性别

    @ExcelProperty(value = "密码", index = 11)
    private String rdPwd;       //密码

    @ExcelProperty(value = "读者类型", index = 3)
    private Integer rdType;       //读者类别

    @ExcelProperty(value = "单位名称", index = 4)
    private String rdDept;      //单位名称

    @ExcelProperty(value = "电话号码", index = 5)
    private String rdPhone;     //电话号码

    @ExcelProperty(value = "邮箱", index = 6)
    private String rdEmail;     //邮箱

    @ExcelProperty(value = "照片", index = 8)
    private String rdPhoto;     //照片

    @ExcelProperty(value = "证件状态", index = 9)
    private String rdStatus;    //证件状态

    @ExcelProperty(value = "办证日期", index = 7)
    private Date rdDateReg;     //办证日期

    @ExcelProperty(value = "已借书数量", index = 10)
    private Integer rdBorrowQty;//已借书数量

    @ExcelProperty(value = "读者角色", index = 12)
    private String rdAdminRoles;//读者角色

    public Reader(String rdID, String rdNamerdname, String rdSex, Integer rdType, String rdDept, String rdPhone, String rdEmail, Date rdDateReg, String rdPhoto, String rdStatus, Integer rdBorrowQty, String rdPwd, String rdAdminRoles) {
        this.rdID = rdID;
        this.rdName = rdName;
        this.rdSex = rdSex;
        this.rdType = rdType;
        this.rdDept = rdDept;
        this.rdPhone = rdPhone;
        this.rdEmail = rdEmail;
        this.rdDateReg = rdDateReg;
        this.rdPhoto = rdPhoto;
        this.rdStatus = rdStatus;
        this.rdBorrowQty = rdBorrowQty;
        this.rdPwd = rdPwd;
        this.rdAdminRoles = rdAdminRoles;
    }

    public Reader() {
        super();
    }

    @NotBlank(message = "{rdID_Message}")
    public String getRdID() {
        return rdID;
    }

    public void setRdID(String rdID) {
        this.rdID = rdID;
    }

    @NotBlank(message = "{rdName_Message}")
    public String getRdName() {
        return rdName;
    }

    public void setRdName(String rdName) {
        this.rdName = rdName;
    }

    @NotBlank(message = "{rdSex_Message}")
    public String getRdSex() {
        return rdSex;
    }

    public void setRdSex(String rdSex) {
        this.rdSex = rdSex;
    }

    @NotBlank(message = "{rdPwd_Message}")
    public String getRdPwd() {
        return rdPwd;
    }

    public void setRdPwd(String rdPwd) {
        this.rdPwd = rdPwd;
    }

    public Integer getRdType() {
        return rdType;
    }

    public void setRdType(Integer rdType) {
        this.rdType = rdType;
    }

    public String getRdDept() {
        return rdDept;
    }

    public void setRdDept(String rdDept) {
        this.rdDept = rdDept;
    }

    public String getRdPhone() {
        return rdPhone;
    }

    public void setRdPhone(String rdPhone) {
        this.rdPhone = rdPhone;
    }

    @Email(message = "{rdEmail_Message")
    public String getRdEmail() {
        return rdEmail;
    }

    public void setRdEmail(String rdEmail) {
        this.rdEmail = rdEmail;
    }

    public String getRdPhoto() {
        return rdPhoto;
    }

    public void setRdPhoto(String rdPhoto) {
        this.rdPhoto = rdPhoto;
    }

    public String getRdStatus() {
        return rdStatus;
    }

    public void setRdStatus(String rdStatus) {
        this.rdStatus = rdStatus;
    }

    public Date getRdDateReg() {
        return rdDateReg;
    }

    public void setRdDateReg(Date rdDateReg) {
        this.rdDateReg = rdDateReg;
    }

    public Integer getRdBorrowQty() {
        return rdBorrowQty;
    }

    public void setRdBorrowQty(Integer rdBorrowQty) {
        this.rdBorrowQty = rdBorrowQty;
    }

    public String getRdAdminRoles() {
        return rdAdminRoles;
    }

    public void setRdAdminRoles(String rdAdminRoles) {
        this.rdAdminRoles = rdAdminRoles;
    }
}
