package com.hbhb.cw.invoice.service.impl;

import com.github.pagehelper.PageHelper;
import com.hbhb.core.bean.BeanConverter;
import com.hbhb.cw.invoice.mapper.InvoiceRewardDetailedMapper;
import com.hbhb.cw.invoice.model.InvoiceRewardDetailed;
import com.hbhb.cw.invoice.rpc.UnitApiExp;
import com.hbhb.cw.invoice.service.InvoiceDetailedService;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailedExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailedImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailedVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardDetailedResVO;
import com.hbhb.springboot.web.view.Page;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yzc
 * @since 2020-10-21
 */
@Service
@Slf4j
public class InvoiceDetailedServiceImpl implements InvoiceDetailedService {

    @Value("${cw.invoice.unit-id.hangzhou}")
    private Integer hangzhou;
    @Value("${cw.invoice.unit-id.benbu}")
    private Integer benbu;

    @Resource
    private UnitApiExp unitApiExp;
    @Resource
    private InvoiceRewardDetailedMapper invoiceRewardDetailedMapper;

    private final List<String> msg = new ArrayList<>();

    @Override
    public Page<InvoiceRewardDetailedResVO> getPageByCont(InvoiceDetailedVO cond, Integer pageNum, Integer pageSize) {
        // 获取所有下属单位
        List<Integer> unitIds = unitApiExp.getSubUnit(cond.getUnitId());
        PageHelper.startPage(pageNum, pageSize);
        List<InvoiceRewardDetailedResVO> list = invoiceRewardDetailedMapper.selectByCond(cond,unitIds);
        int count = invoiceRewardDetailedMapper.countByCond(cond,unitIds);
        return new Page<>(list, (long) count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveInvoiceDetailed(List<InvoiceDetailedImportVO> dataList, Integer unitId) {
        msg.clear();
        // 获取所有的单位缩写名列表
        Map<String, Integer> unitMap = unitApiExp.getUnitMapByShortName();
        if (!unitId.equals(Math.toIntExact(unitMap.get(dataList.get(0).getUnitId())))) {
            msg.add("只能导入同单位的数据");
            return;
        }
        List<InvoiceRewardDetailed> list = BeanConverter.copyBeanList(dataList, InvoiceRewardDetailed.class);
        for (int i = 0; i < list.size(); i++) {
            String reward = dataList.get(i).getReward();
            list.get(i).setReward(new BigDecimal(reward == null ? "0" : reward));
            String tally = dataList.get(i).getTally();
            list.get(i).setTally(new BigDecimal(tally == null ? "0" : tally));
            String ratReward = dataList.get(i).getRatReward();
            list.get(i).setRatReward(new BigDecimal(ratReward == null ? "0" : ratReward));
            String actuallyPaid = dataList.get(i).getActuallyPaid();
            list.get(i).setActuallyPaid(new BigDecimal(actuallyPaid == null ? "0" : actuallyPaid));
            String averagePrice = dataList.get(i).getAveragePrice();
            list.get(i).setAveragePrice(new BigDecimal(averagePrice == null ? "0" : averagePrice));
        }
        for (int i = 0; i < dataList.size(); i++) {
            list.get(i).setUnitId(Math.toIntExact(unitMap.get(dataList.get(i).getUnitId())));
        }
        // 删除该单位下所有数据
        long begin = System.currentTimeMillis();
        if (msg.size() == 0) {
            invoiceRewardDetailedMapper.deleteByUnitId(unitId);
            invoiceRewardDetailedMapper.insertBatch(list);
        }
        log.info("传数据库总共耗时：" + (System.currentTimeMillis() - begin) / 1000 + "s");
    }

    @Override
    public List<InvoiceDetailedExportVO> getListByCont(InvoiceDetailedVO cond) {
        // 获取所有下属单位
        List<Integer> unitIds = unitApiExp.getSubUnit(cond.getUnitId());
        List<InvoiceRewardDetailedResVO> list = invoiceRewardDetailedMapper.selectByCond(cond, unitIds);
        return BeanConverter.copyBeanList(list, InvoiceDetailedExportVO.class);
    }

    @Override
    public List<String> getMsg() {
        return msg;
    }
}
