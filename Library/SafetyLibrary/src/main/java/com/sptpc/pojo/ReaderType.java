package com.sptpc.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

//读者的类别，这个只有系统管理员才有的操作；该类别里包括了不同读者在图书馆里借书的限制
public class ReaderType extends BaseRowModel implements Serializable {
    @ExcelProperty(value = "读者类别", index = 0)
    private Short rdType;                //读者类别

    @ExcelProperty(value = "读者名称", index = 1)
    private String rdTypeName;          //读者名称

    @ExcelProperty(value = "可借书的数量", index = 6)
    private Integer canLendQty;         //可借书的数量

    @ExcelProperty(value = "可借书的天数", index = 2)
    private Integer canLendDay;         //可借书的天数

    @ExcelProperty(value = "可续借的次数", index = 3)
    private Integer canContinueTimes;   //可续借的次数

    @ExcelProperty(value = "罚款率", index = 4)
    private Float punishRate;           //罚款率

    @ExcelProperty(value = "证件的有效期", index = 5)
    private Short dateValid;             //证件的有效期

    public ReaderType(Short rdType, String rdTypeName, Integer canLendQty, Integer canLendDay, Integer canContinueTimes, Float punishRate, Short dateValid) {
        this.rdType = rdType;
        this.rdTypeName = rdTypeName;
        this.canLendQty = canLendQty;
        this.canLendDay = canLendDay;
        this.canContinueTimes = canContinueTimes;
        this.punishRate = punishRate;
        this.dateValid = dateValid;
    }

    public ReaderType() {
        super();
    }

    @DecimalMax(value = "20", message = "{rdType_Int}")
    public Short getRdType() {
        return rdType;
    }

    public void setRdType(Short rdType) {
        this.rdType = rdType;
    }

    @NotBlank(message = "{rdTypeName_Message}")
    public String getRdTypeName() {
        return rdTypeName;
    }

    public void setRdTypeName(String rdTypeName) {
        this.rdTypeName = rdTypeName;
    }

    @DecimalMax(value = "20", message = "{canLendQty_Message}")
    public Integer getCanLendQty() {
        return canLendQty;
    }

    public void setCanLendQty(Integer canLendQty) {
        this.canLendQty = canLendQty;
    }

    public Integer getCanLendDay() {
        return canLendDay;
    }

    public void setCanLendDay(Integer canLendDay) {
        this.canLendDay = canLendDay;
    }

    public Integer getCanContinueTimes() {
        return canContinueTimes;
    }

    public void setCanContinueTimes(Integer canContinueTimes) {
        this.canContinueTimes = canContinueTimes;
    }

    public Float getPunishRate() {
        return punishRate;
    }

    public void setPunishRate(Float punishRate) {
        this.punishRate = punishRate;
    }

    public Short getDateValid() {
        return dateValid;
    }

    public void setDateValid(Short dateValid) {
        this.dateValid = dateValid;
    }
}
