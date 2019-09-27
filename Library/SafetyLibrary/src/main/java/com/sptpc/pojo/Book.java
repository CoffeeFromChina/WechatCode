package com.sptpc.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class Book extends BaseRowModel implements Serializable {
    @NotBlank(message = "不能为空")
    @ExcelProperty(value = "图书序号", index = 0)
    private String bkID;                //图书序号

    @NotBlank(message = "不能为空")
    @ExcelProperty(value = "图书编号", index = 1)
    private String bkCode;              //图书编号

    @NotBlank(message = "不能为空")
    @ExcelProperty(value = "书名", index = 2)
    private String bkName;              //书名

    @ExcelProperty(value = "作者", index = 3)
    private String bkAuthor;            //作者

    @ExcelProperty(value = "出版社", index = 4)
    private String bkPress;             //出版社

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value = "出版日期", index = 5)
    private Date bkDatePress;           //出版日期

    @NotBlank(message = "不能为空")
    @ExcelProperty(value = "ISBN书号", index = 6)
    private String bkISBN;              //ISBN书号

    @NotBlank(message = "不能为空")
    @ExcelProperty(value = "分类号", index = 7)
    private String bkCatalog;           //分类号

    @ExcelProperty(value = "语言", index = 8)
    private String bkLanguage;          //语言

    @ExcelProperty(value = "页数", index = 9)
    private Integer bkPages;            //页数

    @ExcelProperty(value = "价格", index = 10)
    private Float bkPrice;              //价格

    @ExcelProperty(value = "入馆日期", index = 11)
    private Date bkDateIn;              //入馆日期

    @NotBlank(message = "不能为空")
    @Size(min = 0, max = 100, message = "字数超了100个")
    @ExcelProperty(value = "内容简介", index = 12)
    private String bkBrief;             //内容简介

    @ExcelProperty(value = "图书状态", index = 14)
    private String bkCover;             //图书封面

    @ExcelProperty(value = "图书封面", index = 13)
    private String bkStatus;            //图书状态

    public Book() {

    }

    public Book(String bkID, String bkCode, String bkName, String bkAuthor, String bkPress, Date bkDatePress, String bkISBN, String bkCatalog, String bkLanguage, Integer bkPages, Float bkPrice, Date bkDateIn, String bkBrief, String bkCover, String bkStatus) {
        this.bkID = bkID;
        this.bkCode = bkCode;
        this.bkName = bkName;
        this.bkAuthor = bkAuthor;
        this.bkPress = bkPress;
        this.bkDatePress = bkDatePress;
        this.bkISBN = bkISBN;
        this.bkCatalog = bkCatalog;
        this.bkLanguage = bkLanguage;
        this.bkPages = bkPages;
        this.bkPrice = bkPrice;
        this.bkDateIn = bkDateIn;
        this.bkBrief = bkBrief;
        this.bkCover = bkCover;
        this.bkStatus = bkStatus;
    }

    public String getBkID() {
        return bkID;
    }

    public void setBkID(String bkID) {
        this.bkID = bkID;
    }

    public String getBkCode() {
        return bkCode;
    }

    public void setBkCode(String bkCode) {
        this.bkCode = bkCode;
    }

    public String getBkName() {
        return bkName;
    }

    public void setBkName(String bkName) {
        this.bkName = bkName;
    }

    public String getBkAuthor() {
        return bkAuthor;
    }

    public void setBkAuthor(String bkAuthor) {
        this.bkAuthor = bkAuthor;
    }

    public String getBkPress() {
        return bkPress;
    }

    public void setBkPress(String bkPress) {
        this.bkPress = bkPress;
    }

    public Date getBkDatePress() {
        return bkDatePress;
    }

    public void setBkDatePress(Date bkDatePress) {
        this.bkDatePress = bkDatePress;
    }

    public String getBkISBN() {
        return bkISBN;
    }

    public void setBkISBN(String bkISBN) {
        this.bkISBN = bkISBN;
    }

    public String getBkCatalog() {
        return bkCatalog;
    }

    public void setBkCatalog(String bkCatalog) {
        this.bkCatalog = bkCatalog;
    }

    public String getBkLanguage() {
        return bkLanguage;
    }

    public void setBkLanguage(String bkLanguage) {
        this.bkLanguage = bkLanguage;
    }

    public Integer getBkPages() {
        return bkPages;
    }

    public void setBkPages(int bkPages) {
        this.bkPages = bkPages;
    }

    public Float getBkPrice() {
        return bkPrice;
    }

    public void setBkPrice(Float bkPrice) {
        this.bkPrice = bkPrice;
    }

    public Date getBkDateIn() {
        return bkDateIn;
    }

    public void setBkDateIn(Date bkDateIn) {
        this.bkDateIn = bkDateIn;
    }

    public String getBkBrief() {
        return bkBrief;
    }

    public void setBkBrief(String bkBrief) {
        this.bkBrief = bkBrief;
    }

    public String getBkCover() {
        return bkCover;
    }

    public void setBkCover(String bkCover) {
        this.bkCover = bkCover;
    }

    public String getBkStatus() {
        return bkStatus;
    }

    public void setBkStatus(String bkStatus) {
        this.bkStatus = bkStatus;
    }
}
