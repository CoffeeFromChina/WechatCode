package com.xuuuuu.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class Catagory extends BaseRowModel {
    @ExcelProperty(value = "id", index = 0)
    private Integer id;

    @ExcelProperty(value = "name", index = 1)
    private String name;

    public Catagory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Catagory() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
