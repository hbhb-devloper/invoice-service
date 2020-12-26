package com.hbhb.cw.invoice.service.impl;

import com.hbhb.core.bean.BeanConverter;
import com.hbhb.cw.invoice.model.InvoiceTaxpayerCredentials;
import com.hbhb.cw.invoice.service.InvoiceAccountRunService;
import com.hbhb.cw.invoice.service.InvoiceContrastService;
import com.hbhb.cw.invoice.service.InvoiceTaxpayerService;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastVO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yzc
 * @since 2020-10-22
 */
@Service
@Slf4j
public class InvoiceContrastServiceImpl implements InvoiceContrastService {

    @Value("${cw.invoice.unit-id.hangzhou}")
    private Integer hangzhou;
    @Value("${cw.invoice.unit-id.benbu}")
    private Integer benbu;

    @Resource
    private InvoiceAccountRunService invoiceAccountRunService;
    @Resource
    private InvoiceTaxpayerService invoiceTaxpayerService;

    @Override
    public InvoiceContrastResVO getPageByCont(InvoiceContrastReqVO cond, Integer pageNum, Integer pageSize) {
        InvoiceContrastResVO vo = new InvoiceContrastResVO();
        List<InvoiceContrastVO> list = getListByCont(cond);
        vo.setTotal(list.size());
        vo.setList(list.stream().sorted(Comparator.comparing(InvoiceContrastVO::getUnitId))
                .skip((pageNum) * (pageSize-1)).limit(pageSize-1).collect(Collectors.toList()));
        return vo;
    }

    @Override
    public List<InvoiceContrastExportVO> getExportByCont(InvoiceContrastReqVO cond) {
        List<InvoiceContrastVO> listByCont = getListByCont(cond);
        return BeanConverter.copyBeanList(listByCont, InvoiceContrastExportVO.class);
    }

    public List<InvoiceContrastVO> getListByCont(InvoiceContrastReqVO cond) {
        List<InvoiceContrastVO> list = invoiceAccountRunService.getContrastByCond(cond);
        List<InvoiceTaxpayerCredentials> taxpayers = invoiceTaxpayerService.getListByCont(cond.getUnitId(), cond.getChannelNum());

        // channel-> taxpayerCredentials 编号-》纳税人资质
        Map<String, Integer> map = new HashMap<>();
        for (InvoiceTaxpayerCredentials taxpayer : taxpayers) {
            map.put(taxpayer.getChannelNum(), taxpayer.getTaxpayerCredentials());
        }
        // 遍历判断是否为一般纳税人
        for (InvoiceContrastVO contrast : list) {
            if (map.get(contrast.getChannelNum()) == null || contrast.getTaxAmount() == null) {
                continue;
            }
            if (map.get(contrast.getChannelNum()) == 0) {
                contrast.setTaxRate(new BigDecimal("0.06"));
            }
            // 判断如果本月未超十万为0%，超了为3%
            else {
                if (contrast.getReward().compareTo(new BigDecimal(100000)) > 0) {
                    contrast.setTaxRate(new BigDecimal("0.03"));
                } else {
                    contrast.setTaxRate(new BigDecimal("0"));
                }
            }
        }
        // 计算集中化应付不含税
        for (InvoiceContrastVO contrast : list) {
            if (contrast.getTaxRate() == null) {
                continue;
            }
            BigDecimal taxAmount = contrast.getTaxAmount();
            BigDecimal taxRate = new BigDecimal("1").add(contrast.getTaxRate());
            BigDecimal payableSum = contrast.getPayableSum();
            BigDecimal newPayableSum = payableSum.divide(taxRate, 2, BigDecimal.ROUND_DOWN);
            contrast.setPayableSum(newPayableSum);
            if (taxAmount == null) {
                contrast.setTaxAmount(new BigDecimal("0.0"));
                contrast.setReward(newPayableSum);
            } else {
                BigDecimal newTaxAmount = taxAmount.divide(taxRate, 2, BigDecimal.ROUND_DOWN);
                contrast.setTaxAmount(newTaxAmount);
                contrast.setReward(newPayableSum.subtract(newTaxAmount));
            }
        }
        // 比较得到往来账与集中化应付，取小值
        for (int i = list.size()-1; i >= 0; i--) {
            if (list.get(i).getTaxRate()==null){
                list.remove(i);
                continue;
            }
            list.get(i).setThisReward(list.get(i)
                    .getThisReward() == null ? new BigDecimal("0.0") : list.get(i).getThisReward());
            list.get(i).setThisSubsidy(list.get(i)
                    .getThisSubsidy() == null ? new BigDecimal("0.0") : list.get(i).getThisSubsidy());
            list.get(i).setSubtotal(list.get(i)
                    .getSubtotal() == null ? new BigDecimal("0.0") : list.get(i).getSubtotal());
            BigDecimal lowReward = list.get(i).getThisReward()
                    .compareTo(list.get(i).getReward()) < 0 ? list.get(i).getThisReward() : list.get(i).getReward();
            BigDecimal lowSubsidy = list.get(i).getThisSubsidy()
                    .compareTo(list.get(i).getTaxAmount()) < 0 ? list.get(i).getThisSubsidy() : list.get(i).getTaxAmount();
            BigDecimal lowSubtotal = list.get(i).getSubtotal()
                    .compareTo(list.get(i).getPayableSum()) < 0 ? list.get(i).getSubtotal() : list.get(i).getPayableSum();
            list.get(i).setLowReward(lowReward);
            list.get(i).setLowSubsidy(lowSubsidy);
            list.get(i).setLowSubtotal(lowSubtotal);
        }
        return list;
    }

}
