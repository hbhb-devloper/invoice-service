package com.hbhb.cw.invoice.service.impl;

import com.hbhb.core.bean.BeanConverter;
import com.hbhb.cw.invoice.common.InvoiceBuyer;
import com.hbhb.cw.invoice.common.InvoiceGoodsName;
import com.hbhb.cw.invoice.mapper.InvoiceLibraryMapper;
import com.hbhb.cw.invoice.service.Invoice1vatService;
import com.hbhb.cw.invoice.service.InvoiceInspectionService;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectResVat;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionVO;
import com.hbhb.cw.systemcenter.enums.UnitEnum;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * @author yzc
 * @since 2020-09-21
 */
@Service
public class InvoiceInspectionServiceImpl implements InvoiceInspectionService {

    @Resource
    private InvoiceLibraryMapper invoiceLibraryMapper;
    @Resource
    private Invoice1vatService invoice1vatService;

    @Override
    public InvoiceInspectionResVO getList(InvoiceInspectionReqVO cond, Integer pageNum, Integer pageSize) {
        InvoiceInspectionResVO vo = new InvoiceInspectionResVO();
        List<InvoiceInspectionVO> checkList = checkList(cond);
        vo.setList(checkList.stream().sorted(Comparator.comparing(InvoiceInspectionVO::getLineNumber))
                .skip((pageNum) * (pageSize - 1)).limit(pageSize - 1).collect(Collectors.toList()));
        vo.setTotal(checkList.size());
        return vo;
    }

    @Override
    public List<InvoiceInspectionExportVO> getExportList(InvoiceInspectionReqVO cond) {
        List<InvoiceInspectionVO> checkList = checkList(cond);
        List<InvoiceInspectionExportVO> exportList = BeanConverter.copyBeanList(checkList, InvoiceInspectionExportVO.class);
        for (int i = 0; i < exportList.size(); i++) {
            if (checkList.get(i).getInvoiceState() == null) {
                exportList.get(i).setInvoiceState("??????");
            } else {
                exportList.get(i).setInvoiceState("??????");
            }
            exportList.get(i).setLineNumber((i + 1));
        }
        return exportList;
    }


    /**
     * ??????List<InvoiceInspectionVO>
     */
    private List<InvoiceInspectionVO> checkList(InvoiceInspectionReqVO cond) {
        if (UnitEnum.HANGZHOU.value().equals(cond.getUnitId())){
            cond.setUnitId(null);
        }
        List<InvoiceInspectResVat> vatList = invoice1vatService.getListByCond(cond);
        List<InvoiceInspectionVO> allList = BeanConverter.copyBeanList(vatList, InvoiceInspectionVO.class);
        List<InvoiceInspectionVO> list = invoiceLibraryMapper.selectInspectionList(cond);
        // invoiceCode+invoiceNumber -> InvoiceInspectionVO
        Map<String, InvoiceInspectionVO> map = new HashMap<>();
        for (InvoiceInspectionVO vo : list) {
            map.put(vo.getInvoiceCode() + vo.getInvoiceNumber(), vo);
        }
        // ?????????????????????????????????????????????
        for (int i = 0; i < allList.size(); i++) {
            if (map.get(allList.get(i).getInvoiceCode() + allList.get(i).getInvoiceNumber()) != null) {
                allList.set(i, map.get(allList.get(i).getInvoiceCode() + allList.get(i).getInvoiceNumber()));
                if ("1".equals(list.get(i).getInvoiceState())){
                    allList.get(i).setInvoiceState("??????");
                }else {
                    allList.get(i).setInvoiceState("??????");
                }
            } else {
                allList.get(i).setCheck("???????????????????????????");
            }
            allList.get(i).setLineNumber(i + 1);
            allList.get(i).setInvoiceDate(vatList.get(i).getInvoiceDate());
        }
        List<BigDecimal> taxRateList = new ArrayList<>();
        taxRateList.add(new BigDecimal("0"));
        taxRateList.add(new BigDecimal("0.01"));
        taxRateList.add(new BigDecimal("0.03"));
        taxRateList.add(new BigDecimal("0.06"));
        List<String> goodsNames = new ArrayList<>();
        goodsNames.add(InvoiceGoodsName.AGENCY_FEE.value());
        goodsNames.add(InvoiceGoodsName.MODERN_SERVICE.value());
        goodsNames.add(InvoiceGoodsName.MODERN_SERVICE_FEE.value());
        goodsNames.add(InvoiceGoodsName.AGENCY_MODERN_SERVICES.value());
        goodsNames.add(InvoiceGoodsName.MODERN_AGENCY_SERVICES.value());
        goodsNames.add(InvoiceGoodsName.AGENCY_SERVICE_FEE.value());
        for (InvoiceInspectionVO inspectionVO : allList) {

            if (inspectionVO.getCheck() != null) {
                continue;
            }
            if (inspectionVO.getInvoiceState() == null) {
                inspectionVO.setCheck("?????????????????????");
                continue;
            }
            if (inspectionVO.getSellerTax() == null || inspectionVO.getSellerName() == null
                    || inspectionVO.getSellerAccount() == null || inspectionVO.getSellerAccount().length() == 0
                    || inspectionVO.getSellerContact() == null || inspectionVO.getSellerContact().length() == 0) {
                inspectionVO.setCheck("?????????????????????");
                continue;
            }
            if (inspectionVO.getBuyerTax() == null || inspectionVO.getBuyerName() == null
                    || inspectionVO.getBuyerAccount() == null || inspectionVO.getBuyerAccount().length() == 0
                    || inspectionVO.getBuyerContact() == null || inspectionVO.getBuyerContact().length() == 0) {
                inspectionVO.setCheck("??????????????????");
                continue;
            }
            if (inspectionVO.getDrawer() == null || inspectionVO.getDrawer().equals(inspectionVO.getPayee())
                    && inspectionVO.getDrawer().equals(inspectionVO.getReviewer())) {
                inspectionVO.setCheck("????????????????????????????????????");
                continue;
            }
            if (!taxRateList.contains(inspectionVO.getTaxRate())) {
                inspectionVO.setCheck("?????????????????????");
                continue;
            }
            if (!InvoiceBuyer.BUYER_NUMBER.value().equals(inspectionVO.getBuyerTax()) || !InvoiceBuyer.BUYER_NAME.value().equals(inspectionVO.getBuyerName())) {
                inspectionVO.setCheck("???????????????/????????????????????????");
                continue;
            }
            if (!goodsNames.contains(inspectionVO.getGoodsName())) {
                inspectionVO.setCheck("?????????????????????????????????");
            }
        }
        return allList;
    }
}
