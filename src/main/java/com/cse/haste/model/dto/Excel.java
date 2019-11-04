package com.cse.haste.model.dto;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author WangZhenqi
 */
public class Excel {
    /**
     * 名称
     */
    private String name;
    /**
     * 工作簿实例对象
     */
    private Workbook workbook;

    public Excel() {
    }

    public Excel(String name, Workbook workbook) {
        this.name = name;
        this.workbook = workbook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }
}
