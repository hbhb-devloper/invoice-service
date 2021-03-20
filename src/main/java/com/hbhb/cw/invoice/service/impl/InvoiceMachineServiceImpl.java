package com.hbhb.cw.invoice.service.impl;

import com.github.pagehelper.PageHelper;
import com.hbhb.core.bean.BeanConverter;
import com.hbhb.core.utils.DateUtil;
import com.hbhb.cw.invoice.common.DictType;
import com.hbhb.cw.invoice.common.InvoiceBuyer;
import com.hbhb.cw.invoice.common.InvoiceMachineType;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.mapper.InvoiceMachineMapper;
import com.hbhb.cw.invoice.model.InvoiceMachine;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.DictApiExp;
import com.hbhb.cw.invoice.rpc.SysUserApiExp;
import com.hbhb.cw.invoice.service.InvoiceMachineService;
import com.hbhb.cw.invoice.web.vo.InvoiceByCondVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineAddVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceMachineVO;
import com.hbhb.cw.systemcenter.enums.DictCode;
import com.hbhb.cw.systemcenter.vo.DictVO;
import com.hbhb.cw.systemcenter.vo.UserInfo;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * 通用机打发票Service业务层处理
 *
 * @author ruoyi
 * @date 2020-06-02
 */
@Service
@Slf4j
public class InvoiceMachineServiceImpl implements InvoiceMachineService {

    @Resource
    private DictApiExp dictApiExp;

    @Resource
    private InvoiceMachineMapper invoiceMachineMapper;

    @Resource
    private SysUserApiExp sysUserApiExp;


    /**
     * 查询通用机打发票
     *
     * @param machineInvoiceId 通用机打发票ID
     * @return 通用机打发票
     */
    @Override
    public InvoiceMachine selectInvoiceMachineById(Long machineInvoiceId) {
        return invoiceMachineMapper.selectByPrimaryKey(machineInvoiceId);
    }

    /**
     * 导出发票列表
     *
     * @param ids 通用机打发票
     * @return 通用机打发票
     */
    @Override
    public List<InvoiceMachineVO> selectListByIds(List<Long> ids) {
        List<InvoiceMachine> invoiceMachines = invoiceMachineMapper
                .selectListByIds(ids);
        List<InvoiceMachineVO> invoiceMachineVOS = BeanConverter
                .copyBeanList(invoiceMachines, InvoiceMachineVO.class);
        // 设置excel每条数据的行号
        for (int i = 0; i < invoiceMachineVOS.size(); i++) {
            invoiceMachineVOS.get(i).setLineNumber(i + 1);
        }

        return invoiceMachineVOS;
    }

    /**
     * 通过条件查询
     */
    @Override
    public Page<InvoiceMachineResVO> selectInvoiceMachineByCond(Integer pageNum, Integer pageSize,
                                                                InvoiceMachineReqVO invoiceMachineReqVO) {
        //判断是否为管理员
        boolean adminRole = sysUserApiExp.isAdmin(invoiceMachineReqVO.getUserId());
        if (adminRole) {
            invoiceMachineReqVO.setUserId(null);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<InvoiceMachineResVO> list = invoiceMachineMapper
                .selectListByCond(invoiceMachineReqVO);
        //得到invoice_species的字典类
         List<DictVO> dictListByType = dictApiExp
                .getDict(DictType.INVOICE.value(), DictCode.INVOICE_TYPE_MACHINE.value());
        list.forEach(vo -> convertInvoiceResVO(vo, dictListByType));
        for (int i = 0; i < list.size(); i++) {
            int h = (pageNum - 1) * pageSize;
            list.get(i).setLineNumber(String.valueOf(h + i + 1));
        }
        int count = invoiceMachineMapper.countByCond(invoiceMachineReqVO);
        return new Page<>(list, (long) count);
    }

    @Override
    public List<InvoiceMachineVO> listInvoiceMachineByCond(
            InvoiceMachineReqVO invoiceMachineReqVO) {
        AtomicInteger lineNumber = new AtomicInteger(1);
        //判断是否为管理员
        boolean adminRole = sysUserApiExp.isAdmin(invoiceMachineReqVO.getUserId());
        if (adminRole) {
            invoiceMachineReqVO.setUserId(null);
        }
        List<InvoiceMachineResVO> list = invoiceMachineMapper.selectListByCond(invoiceMachineReqVO);
        List<InvoiceMachineVO> invoiceMachineVOS = BeanConverter
                .copyBeanList(list, InvoiceMachineVO.class);
        // 设置excel每条数据的行号
        for (InvoiceMachineVO vo : invoiceMachineVOS) {
            vo.setLineNumber(lineNumber.getAndIncrement());
        }
        return invoiceMachineVOS;
    }

    /**
     * 通用机打发票发票列表字段转换
     */
    private void convertInvoiceResVO(InvoiceMachineResVO vo,  List<DictVO> listByType) {
        for (DictVO sysDict : listByType) {
            if (sysDict.getValue().equals(vo.getInvoiceType())) {
                vo.setInvoiceType(sysDict.getLabel());
            }
        }
    }

    /**
     * 新增通用机打发票
     *
     * @return 结果
     */
    @Override
    public void insertInvoiceMachine(InvoiceMachineAddVO invoiceMachineAddVO, Integer userId) {
        UserInfo user = sysUserApiExp.getUserInfoById(userId);
        judgeInvoice(invoiceMachineAddVO);
        InvoiceMachine invoiceMachine = new InvoiceMachine();
        BeanUtils.copyProperties(invoiceMachineAddVO, invoiceMachine);
        Date date = DateUtil.string2DateYMD(invoiceMachineAddVO.getInvoiceDate());
        invoiceMachine.setInvoiceDate(date);
        //赋值单位名称和导入人员
        invoiceMachine.setUserId(user.getId());
        invoiceMachine.setUnitId(user.getUnitId());
        invoiceMachine.setiTime(new Date());
        invoiceMachine.setBuyerTaxId(InvoiceBuyer.BUYER_NUMBER.value());
        invoiceMachine.setBuyerName(InvoiceBuyer.BUYER_NAME.value());
        invoiceMachine.setInvoiceType(InvoiceBuyer.TYPE.value());
        invoiceMachineMapper.insert(invoiceMachine);
    }

    /**
     * 修改通用机打发票
     *
     * @param invoiceMachine 通用机打发票
     * @return 结果
     */
    @Override
    public int updateInvoiceMachine(InvoiceMachine invoiceMachine) {
        return invoiceMachineMapper.updateByPrimaryKey(invoiceMachine);
    }

    /**
     * 删除通用机打发票对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInvoiceMachineByIds(List<String> ids) {
        List<String> idList = new ArrayList<>();
        for (String id : ids) {
            idList.add(id);
        }
        return invoiceMachineMapper.deleteBatchByIds(idList);
    }

    /**
     * 删除通用机打发票信息
     *
     * @param machineInvoiceId 通用机打发票ID
     * @return 结果
     */
    @Override
    public int deleteInvoiceMachineById(Long machineInvoiceId) {
        return invoiceMachineMapper.deleteByPrimaryKey(machineInvoiceId);
    }

    /**
     * 通过发票号码查询
     */
    @Override
    public InvoiceMachine selectInvoiceMachineByNumber(InvoiceMachineAddVO invoiceMachineAddVO) {
        InvoiceByCondVO invoiceByCondVO = new InvoiceByCondVO();
        BeanUtils.copyProperties(invoiceMachineAddVO, invoiceByCondVO);
        return invoiceMachineMapper.selectByNumber(invoiceByCondVO);
    }

    @Override
    public InvoiceMachine selectById(Long id) {
        return invoiceMachineMapper.selectById(id);
    }

    /**
     * 通过发票种类判断必填项
     */
    private void judgeInvoice(InvoiceMachineAddVO invoiceMachineAddVO) {
        // 通过发票种类（invoiceType）来判断后面的必填项
        String invoiceType = invoiceMachineAddVO.getInvoiceType();
        // 当发票种类为 13-桥闸通行费、 14-一二级公路通行费
        if (InvoiceMachineType.FOURTEEN.getValue().equals(invoiceType)) {
            if (InvoiceMachineType.THIRTEEN.getValue().equals(invoiceType)) {
                // 发票代码 必输
                if (invoiceMachineAddVO.getInvoiceCode() == null) {
                    throw new InvoiceException(InvoiceErrorCode.INVOICE_NOT_CODE);
                }
            }
            if (invoiceMachineAddVO.getReportableAmount() == null) {
                //可报帐金额必输
                throw new InvoiceException(InvoiceErrorCode.INVOICE_NOT_REPORT_AMOUNT);
            }
            // 当；23-航空电子客票行程单、24-火车票、25-其他车票船票
        } else if (InvoiceMachineType.TWENTY_THREE.getValue().equals(invoiceType)) {
            if (InvoiceMachineType.TWENTY_FOUR.getValue().equals(invoiceType)
                    || InvoiceMachineType.TWENTY_FIVE.getValue().equals(invoiceType)) {
                //身份必输
                if (invoiceMachineAddVO.getStateIdentity() == null) {
                    throw new InvoiceException(InvoiceErrorCode.INVOICE_NOT_IDENTITY);
                }
                //地区必输
                if (invoiceMachineAddVO.getDomesticAndForeign() == null) {
                    throw new InvoiceException(InvoiceErrorCode.INVOICE_NOT_AREA);
                }
            }
            //票价必输
            if (invoiceMachineAddVO.getFare() == null) {
                throw new InvoiceException(InvoiceErrorCode.INVOICE_NOT_FARE);
            }
            //民航发展基金必输
            if (invoiceMachineAddVO.getMhFund() == null) {
                throw new InvoiceException(InvoiceErrorCode.INVOICE_NOT_MHFUND);
            }
            //燃油附加费必输
            if (invoiceMachineAddVO.getRyAdditionalFee() == null) {
                throw new InvoiceException(InvoiceErrorCode.INVOICE_NOT_FEE);
            }
            //其他税费必输
            if (invoiceMachineAddVO.getOtherTaxes() == null) {
                throw new InvoiceException(InvoiceErrorCode.INVOICE_NOT_OTHER);
            }

            // 当：21-代扣代缴税收缴款凭证、22-其他可抵扣发票、26-试报账虚拟发票
        } else if (InvoiceMachineType.TWENTY_ONE.getValue().equals(invoiceType)
                || InvoiceMachineType.TWENTY_TWO.getValue().equals(invoiceType)
                || InvoiceMachineType.TWENTY_SIX.getValue()
                .equals(invoiceType)) {
            // 不含税金额必输
            if (invoiceMachineAddVO.getTaxFreeAmount() == null) {
                throw new InvoiceException(InvoiceErrorCode.INVOICE_NOT_AMOUNT);
            }
            // 税码必输
            if (invoiceMachineAddVO.getTaxCode() == null) {
                throw new InvoiceException(InvoiceErrorCode.INVOICE_NOT_TAXCODE);
            }
        }
    }
}
