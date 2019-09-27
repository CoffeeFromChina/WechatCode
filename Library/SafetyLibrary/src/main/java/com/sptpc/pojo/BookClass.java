package com.sptpc.pojo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

//图书的编目工作
public class BookClass implements Serializable {
    private String bkCatalog;   //分类号
    private String bkClassName; //分类名

    public BookClass(String bkCatalog, String bkClassName) {
        this.bkCatalog = bkCatalog;
        this.bkClassName = bkClassName;
    }

    public BookClass() {
        super();
    }

    @NotBlank(message = "不能为空")
    public String getBkCatalog() {
        return bkCatalog;
    }

    public void setBkCatalog(String bkCatalog) {
        this.bkCatalog = bkCatalog;
    }

    @NotBlank(message = "不能为空")
    public String getBkClassName() {
        return bkClassName;
    }

    public void setBkClassName(String bkClassName) {
        this.bkClassName = bkClassName;
    }
}
