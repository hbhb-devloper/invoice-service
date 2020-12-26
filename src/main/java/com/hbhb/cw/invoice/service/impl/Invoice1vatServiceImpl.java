package com.hbhb.cw.invoice.service.impl;

import com.github.pagehelper.PageHelper;
import com.hbhb.core.bean.BeanConverter;
import com.hbhb.core.utils.DateUtil;
import com.hbhb.cw.invoice.common.DictType;
import com.hbhb.cw.invoice.common.InvoiceBuyer;
import com.hbhb.cw.invoice.mapper.Invoice1vatMapper;
import com.hbhb.cw.invoice.model.Invoice1vat;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.DictApiExp;
import com.hbhb.cw.invoice.rpc.SysUserApiExp;
import com.hbhb.cw.invoice.service.Invoice1vatService;
import com.hbhb.cw.invoice.web.vo.Invoice1AddVO;
import com.hbhb.cw.invoice.web.vo.Invoice1ResVO;
import com.hbhb.cw.invoice.web.vo.Invoice1VO;
import com.hbhb.cw.invoice.web.vo.Invoice1vatReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceByCondVO;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectResVat;
import com.hbhb.cw.invoice.web.vo.InvoiceInspectionReqVO;
import com.hbhb.cw.systemcenter.enums.DictCode;
import com.hbhb.cw.systemcenter.vo.DictVO;
import com.hbhb.cw.systemcenter.vo.UserInfo;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * 1%增值税专票Service业务层处理
 */
@Service
@Slf4j
public class Invoice1vatServiceImpl implements Invoice1vatService {

    @Resource
    private Invoice1vatMapper invoice1vatMapper;

    @Resource
    private SysUserApiExp sysUserApiExp;

    @Resource
    private DictApiExp dictApiExp;

    /**
     * 按条件分页获取1%增值税专票列表
     */
    @Override
    public Page<Invoice1ResVO> pageInvoice1vatByCond(Integer pageNum, Integer pageSize,
                                                     Invoice1vatReqVO cond) {
        //判断是否为管理员
        boolean adminRole = sysUserApiExp.isAdmin(cond.getUserId());
        if (adminRole) {
            cond.setUserId(null);
        }
        PageHelper.startPage(pageNum, pageSize);
        //通过条件得到Invoice1ResVO的list
        List<Invoice1ResVO> list = invoice1vatMapper.selectListByCond(cond);
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
        int count = invoice1vatMapper.countByCond(cond);
        return new Page<>(list, (long) count);
    }

    @Override
    public List<Invoice1VO> listInvoice1vatByCond(Invoice1vatReqVO cond) {
        //判断是否为管理员
        boolean adminRole = sysUserApiExp.isAdmin(cond.getUserId());
        if (adminRole) {
            cond.setUserId(null);
        }
        //通过条件得到Invoice1ResVO的list
        List<Invoice1ResVO> list = invoice1vatMapper.selectListByCond(cond);
        List<Invoice1VO> invoice1VOS = BeanConverter.copyBeanList(list, Invoice1VO.class);
        for (int i = 0; i < invoice1VOS.size(); i++) {
            invoice1VOS.get(i).setLineNumber((i + 1));
            String invoiceDate = invoice1VOS.get(i).getInvoiceDate();
            String replace = invoiceDate.substring(0, 4) + "/" + invoiceDate.substring(5, 7) + "/"
                    + invoiceDate.substring(8);
            invoice1VOS.get(i).setInvoiceDate(replace);
        }
        return invoice1VOS;
    }

    /**
     * 1%发票列表字段转换
     */
    private void convertInvoiceResVO(Invoice1ResVO vo,  List<DictVO> dictListByType,
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
     * 导出1%增值税专票列表
     */
    @Override
    public List<Invoice1VO> selectListByIds(List<Long> ids) {
        List<Invoice1vat> invoice1vats = invoice1vatMapper.selectListByIds(ids);
        //得到导出excel表的对象list
        List<Invoice1VO> invoice1VOS = BeanConverter.copyBeanList(invoice1vats, Invoice1VO.class);
        // 设置excel每条数据的行号
        for (int i = 0; i < invoice1VOS.size(); i++) {
            invoice1VOS.get(i).setLineNumber((i + 1));
            Date date = invoice1vats.get(i).getInvoiceDate();
            String invoiceDate = DateUtil.formatDate(date,"yyyy-MM-dd");
            String replace = invoiceDate.substring(0, 4) + "/" + invoiceDate.substring(5, 7) + "/"
                    + invoiceDate.substring(8);
            invoice1VOS.get(i).setInvoiceDate(replace);
        }
        return invoice1VOS;
    }

    /**
     * 通过发票号码查询
     */
    @Override
    public Invoice1vat selectInvoice1vatByNumber(Invoice1AddVO invoice1AddVO) {
        InvoiceByCondVO invoiceByCondVO = new InvoiceByCondVO();
        BeanUtils.copyProperties(invoice1AddVO, invoiceByCondVO);
        return invoice1vatMapper.selectByNumber(invoiceByCondVO);
    }

    /**
     * 新增1%增值税专票
     */
    @Override
    public void insertInvoice1vat(Invoice1AddVO invoice1AddVO, Integer userId) {
        UserInfo user = sysUserApiExp.getUserInfoById(userId);
        Invoice1vat invoice1vat = new Invoice1vat();
        BeanUtils.copyProperties(invoice1AddVO, invoice1vat);
        Date date = DateUtil.string2DateYMD(invoice1AddVO.getInvoiceDate());
        invoice1vat.setInvoiceDate(date);
        invoice1vat.setUserId(user.getId());
        invoice1vat.setUnitId(user.getUnitId());
        invoice1vat.setBuyerTaxId(InvoiceBuyer.BUYER_NUMBER.value());
        invoice1vat.setiTime(new Date());
        invoice1vat.setProjectProperties(InvoiceBuyer.ATTRIBUTES.value());
        invoice1vatMapper.insert(invoice1vat);
    }

    /**
     * 修改1%增值税专票
     */
    @Override
    public void updateInvoice1vat(Invoice1vat invoice1vat) {
        invoice1vatMapper.updateByPrimaryKey(invoice1vat);
    }

    @Override
    public void deleteInvoice1vatByIds(List<String> ids) {
        invoice1vatMapper.deleteBatchByIds(ids);
    }

    @Override
    public void deleteInvoice1vatById(Long id) {
        invoice1vatMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Invoice1vat selectById(Long vat1Id) {
        return invoice1vatMapper.selectById(vat1Id);
    }

    @Override
    public List<InvoiceInspectResVat> getListByCond(InvoiceInspectionReqVO cond) {
        return invoice1vatMapper.selectVatByCond(cond);
    }
}
