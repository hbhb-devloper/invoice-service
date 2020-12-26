package com.hbhb.cw.invoice.service.impl;

import com.github.pagehelper.PageHelper;
import com.hbhb.core.bean.BeanConverter;
import com.hbhb.cw.invoice.common.DictType;
import com.hbhb.cw.invoice.mapper.InvoiceTaxpayerCredentialsMapper;
import com.hbhb.cw.invoice.model.InvoiceTaxpayerCredentials;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.DictApiExp;
import com.hbhb.cw.invoice.rpc.UnitApiExp;
import com.hbhb.cw.invoice.service.InvoiceTaxpayerService;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerShowVO;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerVO;
import com.hbhb.cw.systemcenter.enums.DictCode;
import com.hbhb.cw.systemcenter.vo.DictVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author yzc
 * @since 2020-10-20
 */
@Service
public class InvoiceTaxpayerServiceImpl implements InvoiceTaxpayerService {

    @Resource
    private UnitApiExp unitApiExp;
    @Resource
    private InvoiceTaxpayerCredentialsMapper invoiceTaxpayerCredentialsMapper;
    @Resource
    private DictApiExp dictApiExp;

    private final List<String> msg = new ArrayList<>();

    @Override
    public Page<InvoiceTaxpayerShowVO> getPageByCont(InvoiceTaxpayerVO cond, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<InvoiceTaxpayerResVO>  taxpayers= invoiceTaxpayerCredentialsMapper.selectByCond(cond);
        List<InvoiceTaxpayerShowVO> list = BeanConverter.copyBeanList(taxpayers, InvoiceTaxpayerShowVO.class);
        // 得到字典
         List<DictVO> dictListByType = dictApiExp.getDict(DictType.INVOICE.value(),
                DictCode.TAXPAYER_CREDENTIALS.value());
        HashMap<Integer, String > map = new HashMap<>();
        for (DictVO dictVO : dictListByType) {
            map.put(Integer.valueOf(dictVO.getValue()),dictVO.getLabel());
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setTaxpayerCredentials(map.get(taxpayers.get(i).getTaxpayerCredentials()));
        }
        int count = invoiceTaxpayerCredentialsMapper.countByCond(cond);
        return new Page<>(list, (long) count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveInvoiceTaxpayer(List<InvoiceTaxpayerImportVO> dataList, Integer unitId) {
        msg.clear();
        List<InvoiceTaxpayerCredentials> list = BeanConverter.copyBeanList(dataList, InvoiceTaxpayerCredentials.class);
        // 获取所有的单位缩写名列表
        Map<String, Integer> unitMap = unitApiExp.getUnitMapByShortName();
        if (!unitId.equals(Math.toIntExact(unitMap.get(dataList.get(0).getUnitId())))){
            msg.add("只能导入同单位的数据");
            return;
        }
         List<DictVO> dictListByType = dictApiExp.getDict(DictType.INVOICE.value(),
                DictCode.TAXPAYER_CREDENTIALS.value());
        HashMap<String, Integer> map = new HashMap<>();
        for (DictVO dictVO : dictListByType) {
            map.put(dictVO.getLabel(), Integer.valueOf(dictVO.getValue()));
        }
        for (int i = 0; i < dataList.size(); i++) {
            list.get(i).setUnitId(Math.toIntExact(unitMap.get(dataList.get(i).getUnitId())));
            if (dataList.get(i).getTaxpayerCredentials()==null){
                list.get(i).setTaxpayerCredentials(1);
            }else {
                list.get(i).setTaxpayerCredentials(map.get(dataList.get(i).getTaxpayerCredentials()));
            }
        }
        for (InvoiceTaxpayerCredentials cond : list) {
            if (cond.getUnitId()==null||cond.getChannelNum()==null||cond.getChannelName()==null
                    ||cond.getTaxpayerCredentials()==null){
                msg.add("必填项不能为空");
                return;
            }
        }
        if (msg.size() == 0) {
            invoiceTaxpayerCredentialsMapper.deleteByUnitId(unitId);
            invoiceTaxpayerCredentialsMapper.insertBatch(list);
        }
    }

    @Override
    public List<InvoiceTaxpayerCredentials> getListByCont(Integer unitId, String channerNum) {
        return invoiceTaxpayerCredentialsMapper.selectByChannel(unitId, channerNum);
    }

    @Override
    public List<String> getMsg() {
        return msg;
    }
}
