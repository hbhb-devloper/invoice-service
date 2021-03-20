package com.hbhb.cw.invoice.service.impl;

import com.github.pagehelper.PageHelper;
import com.hbhb.core.bean.BeanConverter;
import com.hbhb.cw.invoice.mapper.InvoiceFocusAccountMapper;
import com.hbhb.cw.invoice.model.InvoiceFocusAccount;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.SysUserApiExp;
import com.hbhb.cw.invoice.rpc.UnitApiExp;
import com.hbhb.cw.invoice.service.InvoiceFocusService;
import com.hbhb.cw.invoice.web.vo.InvoiceFocusAccountResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceFocusImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceFocusVO;
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
 * @since 2020-10-21
 */
@Service
@Slf4j
public class InvoiceFocusServiceImpl implements InvoiceFocusService {

    @Resource
    private UnitApiExp unitApiExp;
    @Resource
    private InvoiceFocusAccountMapper invoiceFocusAccountMapper;
    @Resource
    private SysUserApiExp sysUserApiExp;

    private final List<String> msg = new ArrayList<>();

    @Override
    public Page<InvoiceFocusAccountResVO> getPageByCont(InvoiceFocusVO cond,Integer userId, Integer pageNum, Integer pageSize) {
       UserInfo user = sysUserApiExp.getUserInfoById(userId);
        // 获取所有下属单位
        List<Integer> unitIds = unitApiExp.getSubUnit(user.getUnitId());
        unitIds.add(-1);
        PageHelper.startPage(pageNum, pageSize);
        List<InvoiceFocusAccountResVO> list = invoiceFocusAccountMapper.selectByCond(cond, unitIds);
        int count = invoiceFocusAccountMapper.countByCond(cond, unitIds);
        return new Page<>(list, (long) count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveInvoiceFocus(List<InvoiceFocusImportVO> dataList, Integer userId) {
        msg.clear();
        UserInfo user = sysUserApiExp.getUserInfoById(userId);
        Integer unitId = user.getUnitId();
        List<InvoiceFocusAccount> list = BeanConverter.copyBeanList(dataList, InvoiceFocusAccount.class);
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
            invoiceFocusAccountMapper.deleteByUnitId(unitId);
            invoiceFocusAccountMapper.insertBatch(list);
        }
    }

    @Override
    public List<String> getMsg() {
        return msg;
    }
}
