package com.hbhb.cw.invoice.service.impl;

import com.hbhb.core.bean.BeanConverter;
import com.hbhb.core.utils.DateUtil;
import com.hbhb.cw.invoice.common.InvoiceState;
import com.hbhb.cw.invoice.mapper.InvoiceGoodsMapper;
import com.hbhb.cw.invoice.mapper.InvoiceLibraryMapper;
import com.hbhb.cw.invoice.model.InvoiceGoods;
import com.hbhb.cw.invoice.model.InvoiceLibrary;
import com.hbhb.cw.invoice.service.InvoiceLibraryService;
import com.hbhb.cw.invoice.web.vo.InvoiceGoodsImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceLibraryImportVO;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yzc
 * @since 2020-09-17
 */
@Service
@Slf4j
public class InvoiceLibraryServiceImpl implements InvoiceLibraryService {

    @Resource
    private InvoiceLibraryMapper invoiceLibraryMapper;
    @Resource
    private InvoiceGoodsMapper invoiceGoodsMapper;

    @Override
    public void saveInvoiceLibrary(List<InvoiceLibraryImportVO> dataList) {
        List<InvoiceLibrary> list = BeanConverter.copyBeanList(dataList, InvoiceLibrary.class);
        for (int i = 0; i < list.size(); i++) {
            String amount = dataList.get(i).getAmount();
            if (amount == null) {
                amount = "0";
            }
            String taxAmount = dataList.get(i).getTaxAmount();
            if (taxAmount == null) {
                taxAmount = "0";
            }
            String taxIncludeAmount = dataList.get(i).getTaxIncludeAmount();
            if (taxIncludeAmount == null) {
                taxIncludeAmount = "0";
            }
            if (dataList.get(i).getInvoiceState().equals(InvoiceState.BUDGET_NAME.value())) {
                list.get(i).setInvoiceState(true);
            } else {
                list.get(i).setInvoiceState(false);
            }
            String invoiceDate = dataList.get(i).getInvoiceDate();
            list.get(i).setAmount(new BigDecimal(amount));
            list.get(i).setTaxAmount(new BigDecimal(taxAmount));
            list.get(i).setTaxIncludeAmount(new BigDecimal(taxIncludeAmount));
            list.get(i).setInvoiceDate(DateUtil.formatString(invoiceDate, "yyyy-MM-dd"));
            list.get(i).setCreateTime(new Date());
        }
        invoiceLibraryMapper.insertBatch(list);
    }

    @Override
    public void saveInvoiceGoods(List<InvoiceGoodsImportVO> dataList) {
        List<InvoiceGoods> list = BeanConverter.copyBeanList(dataList, InvoiceGoods.class);
        for (int i = 0; i < list.size(); i++) {
            String amount = dataList.get(i).getAmount();
            if (amount == null || "".equals(amount)) {
                amount = "0";
            }
            list.get(i).setAmount(new BigDecimal(amount));
            String taxAmount = dataList.get(i).getTaxAmount();
            if (taxAmount == null || "".equals(taxAmount)) {
                taxAmount = "0";
            }
            list.get(i).setTaxAmount(new BigDecimal(taxAmount));
            String taxRate = dataList.get(i).getTaxRate();
            if (taxRate == null || "".equals(taxRate)) {
                taxRate = "0";
            }
            list.get(i).setTaxRate(new BigDecimal(taxRate));
            list.get(i).setCreateTime(new Date());
        }
        invoiceGoodsMapper.insertBatch(list);
    }

    @Override
    public String getTime() {
        String s = invoiceLibraryMapper.selectTime();
        if (s == null) {
            return "无更新时间";
        } else {
            return s;
        }
    }
}
