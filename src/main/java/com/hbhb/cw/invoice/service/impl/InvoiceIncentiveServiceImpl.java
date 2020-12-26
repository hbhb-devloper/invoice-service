package com.hbhb.cw.invoice.service.impl;

import com.github.pagehelper.PageHelper;
import com.hbhb.core.bean.BeanConverter;
import com.hbhb.cw.invoice.mapper.InvoiceIncentiveMapper;
import com.hbhb.cw.invoice.model.InvoiceIncentive;
import com.hbhb.cw.invoice.rpc.UnitApiExp;
import com.hbhb.cw.invoice.service.InvoiceIncentiveService;
import com.hbhb.cw.invoice.web.vo.InvoiceIncentiveImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceIncentiveResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceIncentiveVO;
import com.hbhb.springboot.web.view.Page;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yzc
 * @since 2020-10-20
 */
@Service
@Slf4j
public class InvoiceIncentiveServiceImpl implements InvoiceIncentiveService {

    @Value("${cw.invoice.unit-id.hangzhou}")
    private Integer hangzhou;
    @Value("${cw.invoice.unit-id.benbu}")
    private Integer benbu;

    @Resource
    private UnitApiExp unitApiExp;
    @Resource
    private InvoiceIncentiveMapper invoiceIncentiveMapper;

    private final List<String> msg = new ArrayList<>();

    @Override
    public Page<InvoiceIncentiveResVO> getPageByCont(InvoiceIncentiveVO cond, Integer pageNum, Integer pageSize) {
        // 获取所有下属单位
        List<Integer> unitIds = unitApiExp.getSubUnit(cond.getUnitId());
        PageHelper.startPage(pageNum, pageSize);
        List<InvoiceIncentiveResVO> list = invoiceIncentiveMapper.selectByCond(cond, unitIds);
        int count = invoiceIncentiveMapper.countByCond(cond, unitIds);
        return new Page<>(list, (long) count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveInvoiceIncentive(List<InvoiceIncentiveImportVO> dataList, Integer unitId) {
        msg.clear();
        List<InvoiceIncentive> invoiceIncentives = BeanConverter.copyBeanList(dataList, InvoiceIncentive.class);
        // 获取所有的单位缩写名列表
        Map<String, Integer> unitMap = unitApiExp.getUnitMapByShortName();
        if (!unitId.equals(Math.toIntExact(unitMap.get(dataList.get(0).getUnitId())))){
            msg.add("只能导入同单位的名称");
            return;
        }
        for (int i = 0; i < dataList.size(); i++) {
            invoiceIncentives.get(i).setUnitId(Math.toIntExact(unitMap.get(dataList.get(i).getUnitId())));
        }
        if (msg.size()==0) {
            invoiceIncentiveMapper.deleteByUnitId(unitId);
            invoiceIncentiveMapper.insertBatch(invoiceIncentives);
        }
    }

    @Override
    public List<String> getMsg() {
        return msg;
    }
}
