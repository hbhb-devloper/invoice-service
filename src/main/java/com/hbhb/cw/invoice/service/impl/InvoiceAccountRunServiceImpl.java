package com.hbhb.cw.invoice.service.impl;

import com.github.pagehelper.PageHelper;
import com.hbhb.core.bean.BeanConverter;
import com.hbhb.cw.invoice.mapper.InvoiceAccountRunMapper;
import com.hbhb.cw.invoice.model.InvoiceAccountRun;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.SysUserApiExp;
import com.hbhb.cw.invoice.rpc.UnitApiExp;
import com.hbhb.cw.invoice.service.InvoiceAccountRunService;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountRunVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceContrastVO;
import com.hbhb.cw.systemcenter.vo.UserInfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yzc
 * @since 2020-10-19
 */
@Service
@Slf4j
public class InvoiceAccountRunServiceImpl implements InvoiceAccountRunService {


    @Resource
    private UnitApiExp unitApiExp;
    @Resource
    private InvoiceAccountRunMapper invoiceAccountRunMapper;
    @Resource
    private SysUserApiExp sysUserApiExp;

    private final List<String> msg = new ArrayList<>();

    @Override
    public Page<InvoiceAccountRunResVO> getPageByCont(InvoiceAccountRunVO cond, Integer userId, Integer pageNum, Integer pageSize) {
       UserInfo user = sysUserApiExp.getUserInfoById(userId);
        // 获取所有下属单位
        List<Integer> unitIds = unitApiExp.getSubUnit(user.getUnitId());
        unitIds.add(-1);
        PageHelper.startPage(pageNum, pageSize);
        List<InvoiceAccountRunResVO> list = invoiceAccountRunMapper.selectByCond(cond, unitIds);
        int count = invoiceAccountRunMapper.countByCond(cond, unitIds);
        return new Page<>(list, (long) count);
    }

    @Override
    public List<InvoiceAccountRunExportVO> getListByCont(InvoiceAccountRunVO cond,Integer userId) {
       UserInfo user = sysUserApiExp.getUserInfoById(userId);
        // 获取所有下属单位
        List<Integer> unitIds = unitApiExp.getSubUnit(user.getUnitId());
        List<InvoiceAccountRunResVO> list = invoiceAccountRunMapper.selectByCond(cond, unitIds);
        return BeanConverter.copyBeanList(list, InvoiceAccountRunExportVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveInvoiceAccountRun(List<InvoiceAccountRunImportVO> dataList, Integer unitId) {
        msg.clear();
        List<InvoiceAccountRun> list = BeanConverter.copyBeanList(dataList, InvoiceAccountRun.class);
        // 获取所有的单位缩写名列表
        Map<String, Integer> unitMap = unitApiExp.getUnitMapByShortName();
        if (!unitId.equals(Math.toIntExact(unitMap.get(dataList.get(0).getUnitId())))){
            msg.add("只能导入同单位的数据");
            return;
        }
        for (int i = 0; i < dataList.size(); i++) {
            list.get(i).setUnitId(Math.toIntExact(unitMap.get(dataList.get(i).getUnitId())));
        }
        if (msg.size()==0) {
            invoiceAccountRunMapper.deleteByUnitId(unitId);
            invoiceAccountRunMapper.insertBatch(list);
        }
    }

    @Override
    public String getTime(int unitId) {
        String s = invoiceAccountRunMapper.selectTime(unitId);
        if (s == null) {
            return "无更新时间";
        } else {
            return s;
        }
    }

    @Override
    public List<InvoiceContrastVO> getContrastByCond(InvoiceContrastReqVO cond) {
        List<Integer> unitIds = unitApiExp.getSubUnit(cond.getUnitId());
        return invoiceAccountRunMapper.selectContrastByCond(cond,unitIds);
    }

    @Override
    public int getContrastCountByCond(InvoiceContrastReqVO cond) {
        List<Integer> unitIds = unitApiExp.getSubUnit(cond.getUnitId());
        return invoiceAccountRunMapper.selectContrastCountByCond(cond,unitIds);
    }

    @Override
    public List<String> getMsg() {
        return msg;
    }
}
 