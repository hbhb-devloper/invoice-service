package com.hbhb.cw.invoice.service.impl;

import com.github.pagehelper.PageHelper;
import com.hbhb.core.bean.BeanConverter;
import com.hbhb.core.utils.DateUtil;
import com.hbhb.cw.invoice.common.DictType;
import com.hbhb.cw.invoice.mapper.Invoice6vatMapper;
import com.hbhb.cw.invoice.model.Invoice6vat;
import com.hbhb.cw.invoice.rpc.DictApiExp;
import com.hbhb.cw.invoice.rpc.SysUserApiExp;
import com.hbhb.cw.invoice.rpc.UnitApiExp;
import com.hbhb.cw.invoice.service.Invoice6vatService;
import com.hbhb.cw.invoice.web.vo.Invoice6AddVO;
import com.hbhb.cw.invoice.web.vo.Invoice6ResVO;
import com.hbhb.cw.invoice.web.vo.Invoice6VO;
import com.hbhb.cw.invoice.web.vo.Invoice6vatReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceByCondVO;
import com.hbhb.cw.systemcenter.enums.DictCode;
import com.hbhb.cw.systemcenter.vo.DictVO;
import com.hbhb.cw.systemcenter.vo.UserInfo;
import com.hbhb.springboot.web.view.Page;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * 6%增值税专票Service业务层处理
 *
 * @author ruoyi
 * @date 2020-06-02
 */
@Service
@Slf4j
public class Invoice6vatServiceImpl implements Invoice6vatService {


    @Value("${cw.invoice.invoice.buyer-tax-id}")
    private String buyerTaxId;

    @Value("${cw.invoice.invoice.attributes}")
    private String attributes;

    @Resource
    private Invoice6vatMapper invoice6vatMapper;

    @Resource
    private DictApiExp dictApiExp;

    @Resource
    private SysUserApiExp sysUserApiExp;

    @Resource
    private UnitApiExp unitApiExp;


    /**
     * 查询6%增值税专票
     *
     * @param vat6Id 6%增值税专票ID
     * @return 6%增值税专票
     */
    @Override
    public Invoice6vat selectInvoice6vatById(Long vat6Id) {
        return invoice6vatMapper.selectByPrimaryKey(vat6Id);
    }

    /**
     * 导出百分之6增值税列表
     *
     * @param ids 6%增值税专票
     * @return 6%增值税专票
     */
    @Override
    public List<Invoice6VO> selectListByIds(List<Long> ids) {
        List<Invoice6vat> invoice6vats = invoice6vatMapper.selectListByIds(ids);
        List<Invoice6VO> invoice6VOS = BeanConverter.copyBeanList(invoice6vats, Invoice6VO.class);
        // 设置excel每条数据的行号
        for (int i = 0; i < invoice6VOS.size(); i++) {
            invoice6VOS.get(i).setLineNumber(i + 1);
            Date date = invoice6vats.get(i).getInvoiceDate();
            String invoiceDate = DateUtil.formatDate(date, "yyyy-MM-dd");
            String replace = invoiceDate.substring(0, 4) + "/" + invoiceDate.substring(5, 7) + "/"
                    + invoiceDate.substring(8);
            invoice6VOS.get(i).setInvoiceDate(replace);
        }

        return invoice6VOS;
    }

    /**
     * 通过条件查询6%增值税专票列表
     */
    @Override
    public Page<Invoice6ResVO> selectInvoice6vatByCond(Integer pageNum, Integer pageSize,
                                                       Invoice6vatReqVO invoice6vatReqVO) {
        //判断是否为管理员
        boolean adminRole = sysUserApiExp.isAdmin(invoice6vatReqVO.getUserId());
        if (adminRole) {
            invoice6vatReqVO.setUserId(null);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Invoice6ResVO> list = invoice6vatMapper.selectListByCond(invoice6vatReqVO);
        //得到invoiceType的字典类
         List<DictVO> dictListByType = dictApiExp
                .getDict(DictType.INVOICE.value(), DictCode.INVOICE_TYPE_PRO.value());
        //得到project_properties的字典类
         List<DictVO> dictByType = dictApiExp
                .getDict(DictType.INVOICE.value(),
                        DictCode.INVOICE_PROJECT_PROPERTIES.value());
        list.forEach(vo -> convertInvoiceResVO(vo, dictListByType, dictByType));
        for (int i = 0; i < list.size(); i++) {
            int h = (pageNum - 1) * pageSize;
            list.get(i).setLineNumber(String.valueOf(h + i + 1));
        }
        int count = invoice6vatMapper.countByCond(invoice6vatReqVO);
        return new Page<>(list, (long) count);
    }

    @Override
    public List<Invoice6VO> listInvoice6vatByCond(Invoice6vatReqVO cond) {
        //判断是否为管理员
        boolean adminRole = sysUserApiExp.isAdmin(cond.getUserId());
        if (adminRole) {
            cond.setUserId(null);
        }

        List<Invoice6ResVO> list = invoice6vatMapper.selectListByCond(cond);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setLineNumber(String.valueOf(i + 1));
        }
        List<Invoice6VO> invoice6VOS = BeanConverter.copyBeanList(list, Invoice6VO.class);
        for (int i = 0; i < invoice6VOS.size(); i++) {
            invoice6VOS.get(i).setLineNumber(i + 1);
            String invoiceDate = invoice6VOS.get(i).getInvoiceDate();
            String replace = invoiceDate.substring(0, 4) + "/" + invoiceDate.substring(5, 7) + "/"
                    + invoiceDate.substring(8);
            invoice6VOS.get(i).setInvoiceDate(replace);
        }
        return invoice6VOS;
    }

    /**
     * 6%发票列表字段转换
     */
    /**
     * 3%发票列表字段转换
     */
    private void convertInvoiceResVO(Invoice6ResVO vo,  List<DictVO> dictListByType,
                                      List<DictVO> dictByType) {
        for (DictVO sysDict : dictListByType) {
            if (sysDict.getValue().equals(vo.getInvoiceType())) {
                vo.setInvoiceType(sysDict.getLabel());
            }
        }
        for (DictVO sysDict : dictByType) {
            if (sysDict.getValue().equals(vo.getProjectProperties())) {
                vo.setProjectProperties(sysDict.getLabel());
            }
        }
    }


    /**
     * 新增6%增值税专票
     *
     * @return 结果
     */
    @Override
    public void insertInvoice6vat(Invoice6AddVO invoice6AddVO, Integer userId) {
        UserInfo user = sysUserApiExp.getUserInfoById(userId);
        Invoice6vat invoice6vat = new Invoice6vat();
        BeanUtils.copyProperties(invoice6AddVO, invoice6vat);
        Date date = DateUtil.string2DateYMD(invoice6AddVO.getInvoiceDate());
        invoice6vat.setInvoiceDate(date);
        //赋值单位名称和导入人员
        invoice6vat.setUserId(user.getId());
        invoice6vat.setUnitId(user.getUnitId());
        invoice6vat.setiTime(new Date());
        invoice6vat.setBuyerTaxId(buyerTaxId);
        invoice6vat.setProjectProperties(attributes);
        invoice6vatMapper.insert(invoice6vat);
    }

    /**
     * 修改6%增值税专票
     *
     * @param invoice6vat 6%增值税专票
     * @return 结果
     */
    @Override
    public int updateInvoice6vat(Invoice6vat invoice6vat) {
        return invoice6vatMapper.updateByPrimaryKey(invoice6vat);
    }

    /**
     * 删除6%增值税专票对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInvoice6vatByIds(List<String> ids) {
        return invoice6vatMapper.deleteBatchByIds(ids);
    }

    /**
     * 删除6%增值税专票信息
     *
     * @param vat6Id 6%增值税专票ID
     * @return 结果
     */
    @Override
    public int deleteInvoice6vatById(Long vat6Id) {
        return invoice6vatMapper.deleteByPrimaryKey(vat6Id);
    }

    /**
     * 通过发票号码查询发票
     */
    @Override
    public Invoice6vat selectInvoice6vatByNumber(Invoice6AddVO invoice6AddVO) {
        InvoiceByCondVO invoiceByCondVO = new InvoiceByCondVO();
        BeanUtils.copyProperties(invoice6AddVO, invoiceByCondVO);
        return invoice6vatMapper.selectByNumber(invoiceByCondVO);
    }


    @Override
    public Invoice6vat selectById(Long vat6Id) {
        return invoice6vatMapper.selectById(vat6Id);
    }
}
