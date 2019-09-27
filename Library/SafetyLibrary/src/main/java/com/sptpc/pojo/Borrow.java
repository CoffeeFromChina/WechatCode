package com.sptpc.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

//借阅图书的信息
public class Borrow extends BaseRowModel implements Serializable {
    @ExcelProperty(value = "序号", index = 0)
    private int borrowID;

    @NotBlank(message = "不能为空")
    @ExcelProperty(value = "读者序号", index = 1)
    private String rdID;            //读者序号

    @NotBlank(message = "不能为空")
    @ExcelProperty(value = "图书序号", index = 2)
    private String bkID;            //图书序号

    @ExcelProperty(value = "续借次数", index = 3)
    private Integer idContinueTimes;//续借的次数

    @ExcelProperty(value = "借书日期", index = 4)
    private Date idDateOut;         //借书日期

    @ExcelProperty(value = "应还日期", index = 5)
    private Date idDateRetPlan;     //应还日期

    @ExcelProperty(value = "实际还书日期", index = 6)
    private Date idDateRetAct;      //实际还书日期

    @ExcelProperty(value = "超期天数", index = 7)
    private Integer idOverDay;      //超期天数

    @ExcelProperty(value = "超期金额", index = 8)
    private float idOverMoney;      //超期金额

    @ExcelProperty(value = "罚款金额", index = 9)
    private float idPunishMoney;    //罚款金额
    @ExcelProperty(value = "还书情况", index = 10)
    private String isHasReturn;     //是否还书

    @ExcelProperty(value = "借书操作员", index = 11)
    private String operatorLend;    //借书操作员

    @ExcelProperty(value = "还书操作员", index = 12)
    private String operatorRet;     //还书操作员

    public Borrow(Integer borrowID, String rdID, String bkID, Integer idContinueTimes, Date idDateOut, Date idDateRetPlan, Integer idOverDay, Float idOverMoney, Float idPunishMoney, String isHasReturn, String operatorLend, String operatorRet, Date idDateRetAct) {
        this.borrowID = borrowID;
        this.rdID = rdID;
        this.bkID = bkID;
        this.idContinueTimes = idContinueTimes;
        this.idDateOut = idDateOut;
        this.idDateRetPlan = idDateRetPlan;
        this.idOverDay = idOverDay;
        this.idOverMoney = idOverMoney;
        this.idPunishMoney = idPunishMoney;
        this.isHasReturn = isHasReturn;
        this.operatorLend = operatorLend;
        this.operatorRet = operatorRet;
        this.idDateRetAct = idDateRetAct;
    }

    public Borrow() {
        super();
    }

    public Integer getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(Integer borrowID) {
        this.borrowID = borrowID;
    }

    public String getRdID() {
        return rdID;
    }

    public void setRdID(String rdID) {
        this.rdID = rdID;
    }

    public String getBkID() {
        return bkID;
    }

    public void setBkID(String bkID) {
        this.bkID = bkID;
    }

    public Integer getIdContinueTimes() {
        return idContinueTimes;
    }

    public void setIdContinueTimes(Integer idContinueTimes) {
        this.idContinueTimes = idContinueTimes;
    }

    public Date getIdDateOut() {
        return idDateOut;
    }

    public void setIdDateOut(Date idDateOut) {
        this.idDateOut = idDateOut;
    }

    public Date getIdDateRetPlan() {
        return idDateRetPlan;
    }

    public void setIdDateRetPlan(Date idDateRetPlan) {
        this.idDateRetPlan = idDateRetPlan;
    }

    public Date getIdDateRetAct() {
        return idDateRetAct;
    }

    public void setIdDateRetAct(Date idDateRetAct) {
        this.idDateRetAct = idDateRetAct;
    }

    public Integer getIdOverDay() {
        return idOverDay;
    }

    public void setIdOverDay(Integer idOverDay) {
        this.idOverDay = idOverDay;
    }

    public Float getIdOverMoney() {
        return idOverMoney;
    }

    public void setIdOverMoney(float idOverMoney) {
        this.idOverMoney = idOverMoney;
    }

    public Float getIdPunishMoney() {
        return idPunishMoney;
    }

    public void setIdPunishMoney(float idPunishMoney) {
        this.idPunishMoney = idPunishMoney;
    }

    public String getIsHasReturn() {
        return isHasReturn;
    }

    public void setIsHasReturn(String isHasReturn) {
        this.isHasReturn = isHasReturn;
    }

    public String getOperatorLend() {
        return operatorLend;
    }

    public void setOperatorLend(String operatorLend) {
        this.operatorLend = operatorLend;
    }

    public String getOperatorRet() {
        return operatorRet;
    }

    public void setOperatorRet(String operatorRet) {
        this.operatorRet = operatorRet;
    }
}
