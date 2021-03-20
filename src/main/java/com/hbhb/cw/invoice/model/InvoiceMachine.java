package com.hbhb.cw.invoice.model;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceMachine {

    private Long machineInvoiceId;

    private String invoiceType;

    private String invoiceCode;

    private String invoiceNumber;

    private Date invoiceDate;

    private String stateIdentity;

    private String domesticAndForeign;

    private BigDecimal taxFreeAmount;

    private String taxCode;

    private BigDecimal fare;

    private BigDecimal mhFund;

    private BigDecimal ryAdditionalFee;

    private BigDecimal otherTaxes;

    private BigDecimal money;

    private BigDecimal reportableAmount;

    private String buyerTaxId;

    private String buyerName;

    private String salesTaxNumber;

    private String sellerName;

    private Integer unitId;

    private Date iTime;

    private Integer userId;

    public Long getMachineInvoiceId() {
        return machineInvoiceId;
    }

    public void setMachineInvoiceId(Long machineInvoiceId) {
        this.machineInvoiceId = machineInvoiceId;
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

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getStateIdentity() {
        return stateIdentity;
    }

    public void setStateIdentity(String stateIdentity) {
        this.stateIdentity = stateIdentity == null ? null : stateIdentity.trim();
    }

    public String getDomesticAndForeign() {
        return domesticAndForeign;
    }

    public void setDomesticAndForeign(String domesticAndForeign) {
        this.domesticAndForeign = domesticAndForeign == null ? null : domesticAndForeign.trim();
    }

    public BigDecimal getTaxFreeAmount() {
        return taxFreeAmount;
    }

    public void setTaxFreeAmount(BigDecimal taxFreeAmount) {
        this.taxFreeAmount = taxFreeAmount;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode == null ? null : taxCode.trim();
    }

    public BigDecimal getFare() {
        return fare;
    }

    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }

    public BigDecimal getMhFund() {
        return mhFund;
    }

    public void setMhFund(BigDecimal mhFund) {
        this.mhFund = mhFund;
    }

    public BigDecimal getRyAdditionalFee() {
        return ryAdditionalFee;
    }

    public void setRyAdditionalFee(BigDecimal ryAdditionalFee) {
        this.ryAdditionalFee = ryAdditionalFee;
    }

    public BigDecimal getOtherTaxes() {
        return otherTaxes;
    }

    public void setOtherTaxes(BigDecimal otherTaxes) {
        this.otherTaxes = otherTaxes;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getReportableAmount() {
        return reportableAmount;
    }

    public void setReportableAmount(BigDecimal reportableAmount) {
        this.reportableAmount = reportableAmount;
    }

    public String getBuyerTaxId() {
        return buyerTaxId;
    }

    public void setBuyerTaxId(String buyerTaxId) {
        this.buyerTaxId = buyerTaxId == null ? null : buyerTaxId.trim();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getSalesTaxNumber() {
        return salesTaxNumber;
    }

    public void setSalesTaxNumber(String salesTaxNumber) {
        this.salesTaxNumber = salesTaxNumber == null ? null : salesTaxNumber.trim();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
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