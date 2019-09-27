package com.sptpc.pojo;

import java.util.Date;

//该类为了服务数据信息的分页效果和临界条件服务的，在工程的发展中会陆续添加一些属性，是个可变的类
//分页的实体对象
public class Parameter {
    private String rdID;
    private int start;
    private int end;
    private String bkID;
    private String bkName;
    private String bkStatus;
    private Integer rdBorrowQty;//借书量
    private Date rdDateReg;     //办证日期
    private String rdStatus;    //读者状态
    private String isHasReturn;

    public String getIsHasReturn() {
        return isHasReturn;
    }

    public void setIsHasReturn(String isHasReturn) {
        this.isHasReturn = isHasReturn;
    }

    public String getRdStatus() {
        return rdStatus;
    }

    public void setRdStatus(String rdStatus) {
        this.rdStatus = rdStatus;
    }

    public Integer getRdBorrowQty() {
        return rdBorrowQty;
    }

    public void setRdBorrowQty(Integer rdBorrowQty) {
        this.rdBorrowQty = rdBorrowQty;
    }

    public Date getRdDateReg() {
        return rdDateReg;
    }

    public void setRdDateReg(Date rdDateReg) {
        this.rdDateReg = rdDateReg;
    }

    public String getBkStatus() {
        return bkStatus;
    }

    public void setBkStatus(String bkStatus) {
        this.bkStatus = bkStatus;
    }

    public String getBkName() {
        return bkName;
    }

    public void setBkName(String bkName) {
        this.bkName = bkName;
    }

    public String getBkID() {
        return bkID;
    }

    public void setBkID(String bkID) {
        this.bkID = bkID;
    }

    public String getRdID() {
        return rdID;
    }

    public void setRdID(String rdID) {
        this.rdID = rdID;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }


}
