package com.hbhb.cw.invoice.service.impl;

import com.github.pagehelper.PageHelper;
import com.hbhb.core.bean.BeanConverter;
import com.hbhb.cw.invoice.common.DictType;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.mapper.InvoiceAccountMapper;
import com.hbhb.cw.invoice.mapper.InvoiceRewardMapper;
import com.hbhb.cw.invoice.mapper.InvoiceSubsidyMapper;
import com.hbhb.cw.invoice.model.InvoiceAccount;
import com.hbhb.cw.invoice.model.InvoiceReward;
import com.hbhb.cw.invoice.model.InvoiceSubsidy;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.DictApiExp;
import com.hbhb.cw.invoice.rpc.SysUserApiExp;
import com.hbhb.cw.invoice.rpc.UnitApiExp;
import com.hbhb.cw.invoice.service.InvoiceRewardService;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceAccountVO;
import com.hbhb.cw.invoice.web.vo.InvoiceCheckExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceCheckVO;
import com.hbhb.cw.invoice.web.vo.InvoiceDetailsExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMonthExportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceRewardResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceSubsidyImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceSubsidyVO;
import com.hbhb.cw.systemcenter.enums.DictCode;
import com.hbhb.cw.systemcenter.enums.UnitEnum;
import com.hbhb.cw.systemcenter.vo.DictVO;
import com.hbhb.cw.systemcenter.vo.UserInfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import static java.math.BigDecimal.ZERO;

/**
 * @author yzc
 * @since 2020-09-09
 */
@Service
@Slf4j
public class InvoiceRewardServiceImpl implements InvoiceRewardService {


    @Resource
    private InvoiceRewardMapper invoiceRewardMapper;
    @Resource
    private InvoiceSubsidyMapper invoiceSubsidyMapper;
    @Resource
    private InvoiceAccountMapper invoiceAccountMapper;
    @Resource
    private UnitApiExp unitApiExp;
    @Resource
    private DictApiExp dictApiExp;
    @Resource
    private SysUserApiExp sysUserApiExp;


    private final List<String> msg = new ArrayList<>();


    @Override
    public Page<InvoiceRewardResVO> getListByCont(InvoiceRewardReqVO cond, Integer userId, Integer pageNum, Integer pageSize) {
       UserInfo user = sysUserApiExp.getUserInfoById(userId);
        // 获取所有下属单位
        List<Integer> unitIds = unitApiExp.getSubUnit(user.getUnitId());
        unitIds.add(-1);
        PageHelper.startPage(pageNum, pageSize);
        List<InvoiceRewardResVO> list = invoiceRewardMapper.selectByCond(cond, unitIds);
        int count = invoiceRewardMapper.countByCond(cond, unitIds);
        return new Page<>(list, (long) count);
    }


    @Override
    public void saveInvoiceDetails(List<InvoiceRewardImportVO> dataList, String importDate, Integer userId, BigDecimal taxRate) {
        msg.clear();
        log.info("导入开始了");
        UserInfo user = sysUserApiExp.getUserInfoById(userId);
        Integer unitId = user.getUnitId();
        // 判断用户是否能进行导入操作
        if (UnitEnum.HANGZHOU.value().equals(unitId) || UnitEnum.BENBU.value().equals(unitId)) {
            msg.add("该用户无法导入");
            return;
        }
        String reimbursementJournal = dataList.get(2).getSerialNumber();
        int count = invoiceRewardMapper.countBySerialNumber(reimbursementJournal);
        if (count != 0) {
            msg.add("报账流水相同");
            return;
        }
        // 酬金金额合计
        BigDecimal commSum = new BigDecimal("0.00");
        // 应付金额合计
        BigDecimal amountSum = new BigDecimal("0.00");
        // 本次支付合计
        BigDecimal paySum = new BigDecimal("0.00");
        // 本次实付合计
        BigDecimal actualSum = new BigDecimal("0.00");
        // 代缴税费合计
        BigDecimal taxSum = new BigDecimal("0.00");
        String unitName = dataList.get(0).getSerialNumber();
        String name = unitName.substring(8, 10);
        // 获取所有的单位缩写名列表
        Map<String, Integer> unitMap = unitApiExp.getUnitMapByShortName();
        Integer deptId = unitMap.get(name);
        if (deptId == null) {
            throw new InvoiceException(InvoiceErrorCode.INVOICE_DATA_IMPORT_ERROR);
        } else if (Math.toIntExact(deptId) != unitId) {
            throw new InvoiceException(InvoiceErrorCode.INVOICE_DATA_IMPORT_ERROR);
        }
        if (dataList.size() >= 2) {
            for (int i = 0; i < 2; i++) {
                dataList.remove(dataList.get(0));
            }
        }
        List<InvoiceReward> list = BeanConverter.copyBeanList(dataList, InvoiceReward.class);
        if (dataList.size() >= 2) {
            list.remove(list.get(list.size() - 1));
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSerialNumber() == null) {
                throw new InvoiceException(InvoiceErrorCode.LOCK_OF_INVOICE_ROLE);
            }
            if (!list.get(i).getSerialNumber().equals(reimbursementJournal)) {
                msg.add("第" + (i + 3) + "行的流水账号重复");
                return;
            }
            list.get(i).setImportDate(importDate);
            list.get(i).setUnitId(unitId);
            list.get(i).setTaxRate(taxRate);
            BigDecimal commission = new BigDecimal(dataList.get(i).getAmount() == null ? "0" : dataList.get(i).getAmount());
            BigDecimal amountDue = new BigDecimal(dataList.get(i).getAmountDue() == null ? "0" : dataList.get(i).getAmountDue());
            BigDecimal payment = new BigDecimal(dataList.get(i).getPayment() == null ? "0" : dataList.get(i).getPayment());
            BigDecimal actualPayment = new BigDecimal(dataList.get(i).getActualPayment() == null ? "0" : dataList.get(i).getActualPayment());
            BigDecimal taxPayment = new BigDecimal(dataList.get(i).getTaxPayment() == null ? "0" : dataList.get(i).getTaxPayment());
            // 判断代缴税费是否为0（代缴税费必定为0）
            if (ZERO.compareTo(taxPayment) != 0) {
                msg.add("第" + (i + 3) + "行的代缴税费不为0");
                return;
            }
            if (amountDue.subtract(actualPayment).intValue() < 0) {
                msg.add("第" + (i + 3) + "行的应付金额（元）小于本次实付（元）");
                return;
            }
            commSum = commSum.add(commission);
            amountSum = amountSum.add(amountDue);
            paySum = paySum.add(payment);
            actualSum = actualSum.add(actualPayment);
            taxSum = taxSum.add(taxPayment);
            list.get(i).setAmount(commission);
            list.get(i).setAmountDue(amountSum);
            list.get(i).setPayment(paySum);
            list.get(i).setActualPayment(actualSum);
            list.get(i).setTaxPayment(taxSum);
        }
        // 得到excel表中合计的值
        String commission = dataList.get(dataList.size() - 1).getAmount();
        String amountDue = dataList.get(dataList.size() - 1).getAmountDue();
        String payment = dataList.get(dataList.size() - 1).getPayment();
        String actualPayment = dataList.get(dataList.size() - 1).getActualPayment();
        commission = commission == null ? "0" : commission;
        amountDue = amountDue == null ? "0" : amountDue;
        payment = payment == null ? "0" : payment;
        actualPayment = actualPayment == null ? "0" : actualPayment;
        log.info("commSum" + commSum.toString());
        log.info("commission" + commission);
        log.info("amountSum" + amountSum.toString());
        log.info("amountDue" + amountDue);
        log.info("paySum" + paySum.toString());
        log.info("payment" + payment);
        log.info("actualPayment" + actualPayment);
        log.info("actualSum" + actualSum.toString());
        // 判断excel表中合计与真实合计是否匹配
        if (commSum.compareTo(new BigDecimal(commission)) != 0
                || amountSum.compareTo(new BigDecimal(amountDue)) != 0
                || paySum.compareTo(new BigDecimal(payment)) != 0
                || actualSum.compareTo(new BigDecimal(actualPayment)) != 0) {
            msg.add("第" + (list.size() + 2) + "行的合计有误");
            return;
        }
        if (msg.size() == 0) {
            invoiceRewardMapper.insertBatch(list);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveInvoiceSubsidy(List<InvoiceSubsidyImportVO> dataList, String importDate, Integer userId) {
        msg.clear();
        UserInfo user = sysUserApiExp.getUserInfoById(userId);
        Integer unitId = user.getUnitId();
        Map<String, Integer> unitMap = unitApiExp.getUnitMapByShortName();
        if (dataList.size() != 0 && !unitMap.get(dataList.get(0).getUnitName()).equals(unitId)) {
            msg.add("只能导入同单位的数据");
            return;
        }
        invoiceSubsidyMapper.deleteByMonth(importDate, unitId);
        List<InvoiceSubsidy> list = new ArrayList<>();
        for (InvoiceSubsidyImportVO data : dataList) {
            list.add(InvoiceSubsidy.builder()
                    .channelNumber(data.getChannelNumber())
                    .taxSubsidy(data.getTaxSubsidy())
                    .unitId(Math.toIntExact(unitMap.get(data.getUnitName())))
                    .importDate(importDate)
                    .build());
        }
        invoiceSubsidyMapper.insertBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveInvoiceAccount(List<InvoiceAccountImportVO> dataList, Integer userId) {
        msg.clear();
        UserInfo user = sysUserApiExp.getUserInfoById(userId);
        Integer unitId = user.getUnitId();
        Map<String, Integer> unitMap = unitApiExp.getUnitMapByShortName();
        if (dataList.size() != 0 && !unitMap.get(dataList.get(0).getUnitName()).equals(unitId)) {
            msg.add("只能导入同单位的数据");
            return;
        }
        invoiceAccountMapper.deleteByUnitId(unitId);
        ArrayList<InvoiceAccount> list = new ArrayList<>();
        for (InvoiceAccountImportVO data : dataList) {
            list.add(InvoiceAccount.builder()
                    .channelName(data.getChannelName())
                    .channelNumber(data.getChannelNumber())
                    .amount(data.getAmount() == null ? new BigDecimal("0.0") : data.getAmount())
                    .currentAccount(data.getCurrentAccount())
                    .subsidy(data.getSubsidy() == null ? new BigDecimal("0.0") : data.getSubsidy())
                    .subtotal(data.getSubtotal() == null ? new BigDecimal("0.0") : data.getSubtotal())
                    .unitId(Math.toIntExact(unitMap.get(data.getUnitName())))
                    .build());
        }
        invoiceAccountMapper.insertBatch(list);
    }

    @Override
    public List<InvoiceDetailsExportVO> getDetailsExportByCond(InvoiceRewardReqVO cond, Integer userId) {
       UserInfo user = sysUserApiExp.getUserInfoById(userId);
        // 获取所有下属单位
        List<Integer> unitIds = unitApiExp.getSubUnit(user.getUnitId());
        unitIds.add(-1);
        List<InvoiceRewardResVO> listByCont = invoiceRewardMapper.selectByCond(cond, unitIds);

        if (listByCont == null || listByCont.size() == 0) {
            return new ArrayList<InvoiceDetailsExportVO>();
        }
        List<InvoiceDetailsExportVO> list = BeanConverter.copyBeanList(listByCont, InvoiceDetailsExportVO.class);
        List<DictVO> dictListByType = dictApiExp.getDict(DictType.INVOICE.value(),
                DictCode.INVOICE_TAX_TYPE.value());
        Map<BigDecimal, String> map = new HashMap<>();
        for (DictVO DictVO : dictListByType) {
            map.put(new BigDecimal(DictVO.getValue()), DictVO.getLabel());
        }
        for (InvoiceDetailsExportVO details : list) {
            BigDecimal amountDue = new BigDecimal(details.getAmountDue());
            BigDecimal actualPayment = new BigDecimal(details.getActualPayment());
            // 税率
            BigDecimal taxRate = details.getTaxRate();
            BigDecimal tax = taxRate.add(new BigDecimal(1));
            details.setVerification(amountDue.subtract(actualPayment));
            details.setTaxFreeAmount(actualPayment.divide(taxRate.add(tax), 2));
            details.setInvoiceType(map.get(new BigDecimal("0.00").compareTo(details.getTaxRate()) == 0 ? new BigDecimal("0") : details.getTaxRate()));
        }
        return list;
    }

    @Override
    public List<InvoiceMonthExportVO> getMonthExportByCond(InvoiceRewardReqVO cond, Integer userId) {
       UserInfo user = sysUserApiExp.getUserInfoById(userId);
        // 获取所有下属单位
        List<Integer> unitIds = unitApiExp.getSubUnit(user.getUnitId());
        unitIds.add(-1);
        List<InvoiceMonthExportVO> list = invoiceRewardMapper.censusByCond(cond,unitIds);

        // 通过字典得到发票类型
        List<DictVO> dictListByType = dictApiExp.getDict(DictType.INVOICE.value(),
                DictCode.INVOICE_TAX_TYPE.value());
        Map<BigDecimal, String> map = new HashMap<>();
        for (DictVO dictVO : dictListByType) {
            map.put(new BigDecimal(dictVO.getValue()), dictVO.getLabel());
        }
        for (InvoiceMonthExportVO invoiceMonthExportVO : list) {
            // 如果发票类型是0.00的时候税率为0
            if (invoiceMonthExportVO.getTaxRate().compareTo(new BigDecimal("0.00")) == 0) {
                invoiceMonthExportVO.setTaxRate(new BigDecimal(0));
            }
            // 税率
            BigDecimal taxRate = invoiceMonthExportVO.getTaxRate();
            invoiceMonthExportVO.setInvoiceType(map.get(taxRate));
            BigDecimal tax = taxRate.add(new BigDecimal(1));
            // 不含税实付金额
            BigDecimal divide = invoiceMonthExportVO.getActualPaymentSum()
                    .divide(tax, 2);
            invoiceMonthExportVO.setTaxFreeAmountSum(divide);
        }
        return list;

    }

    @Override
    public List<InvoiceCheckExportVO> getCheckExportByCond(InvoiceRewardReqVO cond, Integer userId) {
       UserInfo user = sysUserApiExp.getUserInfoById(userId);
        // 获取所有下属单位
        List<Integer> unitIds = unitApiExp.getSubUnit(user.getUnitId());
        unitIds.add(-1);
        List<InvoiceCheckVO> list = invoiceRewardMapper.checkListByCond(cond,unitIds);
        // 往来账列表查询
        List<InvoiceAccountVO> accountVOList = invoiceAccountMapper.selectList();
        // unitName + channelNumber -> accountAmount
        HashMap<String, BigDecimal> amountMap = new HashMap<>();
        // unitName + channelNumber -> accountSubsidy
        HashMap<String, BigDecimal> subsidyMap = new HashMap<>();
        // unitName + channelNumber -> accountSum
        HashMap<String, BigDecimal> sumMap = new HashMap<>();
        for (InvoiceAccountVO accountVO : accountVOList) {
            amountMap.put(accountVO.getUnitName() + accountVO.getChannelNumber(), accountVO.getAccountAmount());
            subsidyMap.put(accountVO.getUnitName() + accountVO.getChannelNumber(), accountVO.getAccountSubsidy());
            sumMap.put(accountVO.getUnitName() + accountVO.getChannelNumber(), accountVO.getAccountSum());
        }
        // 综合补贴列表
        List<InvoiceSubsidyVO> subsidyVOList = invoiceSubsidyMapper.selectListByMonth(cond.getImportDate());
        // unitName + channelNumber -> taxSubsidy
        HashMap<String, BigDecimal> subMap = new HashMap<>();
        for (InvoiceSubsidyVO subsidyVO : subsidyVOList) {
            subMap.put(subsidyVO.getUnitName() + subsidyVO.getChannelNumber(), subsidyVO.getTaxSubsidy());
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            String channel = list.get(i).getUnitName() + list.get(i).getChannelNumber();
            // 得到往来账酬金
            BigDecimal accountAmount = amountMap.get(channel);
            if (accountAmount == null) {
                list.remove(i);
                continue;
            }
            list.get(i).setAccountAmount(accountAmount);

            // 相减判断是否超额
            if (list.get(i).getAmountSum() == null) {
                list.get(i).setAmountSum(new BigDecimal("0.0"));
            }
            BigDecimal rewSubtract = accountAmount.subtract(list.get(i).getAmountSum());
            if (rewSubtract.compareTo(ZERO) >= 0) {
                list.get(i).setAmountCheck("超额结算0");
            }
            // 获得补贴金额（含税）
            BigDecimal taxSub = subMap.get(channel);
            if (taxSub == null) {
                taxSub = new BigDecimal("0.0");
            }
            // 税率
            BigDecimal taxRate = list.get(i).getTaxRate();
            BigDecimal tax = taxRate.add(new BigDecimal(1));
            // 不含税实付金额
            BigDecimal divide = taxSub.divide(taxRate.add(tax), 2);
            list.get(i).setSubsidySum(divide);

            // 得到往来账综合补贴
            BigDecimal accountSubsidy = subsidyMap.get(channel);
            if (accountSubsidy == null) {
                accountSubsidy = new BigDecimal("0.0");
            }
            list.get(i).setAccountSubsidy(accountSubsidy);
            // 相减判断是否超额
            BigDecimal assSubtract = accountSubsidy.subtract(divide);
            if (assSubtract.compareTo(ZERO) >= 0) {
                list.get(i).setSubsidyCheck("超额结算0");
            }
            // 求和项:小计（不含税）
            BigDecimal sum = list.get(i).getAmountSum().add(divide);
            list.get(i).setSum(sum);

            // 往来帐小计
            BigDecimal accountSum = sumMap.get(channel);
            if (accountSum == null) {
                accountSum = new BigDecimal("0.0");
            }
            list.get(i).setAccountSum(accountSum);

            BigDecimal sumSubtract = accountSum.subtract(sum);
            if (sumSubtract.compareTo(ZERO) >= 0) {
                list.get(i).setSumCheck("超额结算0");
            }

            // 账期赋值
            list.get(i).setDate(cond.getImportDate());
        }
        return BeanConverter.copyBeanList(list, InvoiceCheckExportVO.class);
    }

    @Override
    public List<String> getMsg() {
        return msg;
    }

    @Override
    public void deleteBySerialNumber(List<String> list) {
        list.add("");
        invoiceRewardMapper.deleteBySerialNumber(list);
    }

}
