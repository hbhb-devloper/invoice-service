package com.hbhb.cw.invoice.model;

import java.math.BigDecimal;
import java.util.Date;

public class Invoice6vat {

    private Long vat6Id;

    private String invoiceType;

    private String invoiceCode;

    private String invoiceNumber;

    private Integer invoicePageNumber;

    private Date invoiceDate;

    private BigDecimal taxFreeAmount;

    private String buyerTaxId;

    private String projectProperties;

    private String tableNumber;

    private Integer unitId;

    private Date iTime;

    private Integer userId;

    public Long getVat6Id() {
        return vat6Id;
    }

    public void setVat6Id(Long vat6Id) {
        this.vat6Id = vat6Id;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode == null ? null : invoiceCode.trim();
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber == null ? null : invoiceNumber.trim();
    }

    public Integer getInvoicePageNumber() {
        return invoicePageNumber;
    }

    public void setInvoicePageNumber(Integer invoicePageNumber) {
        this.invoicePageNumber = invoicePageNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getTaxFreeAmount() {
        return taxFreeAmount;
    }

    public void setTaxFreeAmount(BigDecimal taxFreeAmount) {
        this.taxFreeAmount = taxFreeAmount;
    }

    public String getBuyerTaxId() {
        return buyerTaxId;
    }

    public void setBuyerTaxId(String buyerTaxId) {
        this.buyerTaxId = buyerTaxId == null ? null : buyerTaxId.trim();
    }

    public String getProjectProperties() {
        return projectProperties;
    }

    public void setProjectProperties(String projectProperties) {
        this.projectProperties = projectProperties == null ? null : projectProperties.trim();
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber == null ? null : tableNumber.trim();
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Date getiTime() {
        return iTime;
    }

    public void setiTime(Date iTime) {
        this.iTime = iTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}