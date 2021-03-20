package com.hbhb.cw.invoice.service.impl;

import com.github.pagehelper.PageHelper;
import com.hbhb.core.bean.BeanConverter;
import com.hbhb.core.utils.DateUtil;
import com.hbhb.cw.invoice.common.DictType;
import com.hbhb.cw.invoice.common.InvoiceBuyer;
import com.hbhb.cw.invoice.mapper.Invoice3vatMapper;
import com.hbhb.cw.invoice.model.Invoice3vat;
import com.hbhb.cw.invoice.model.Page;
import com.hbhb.cw.invoice.rpc.DictApiExp;
import com.hbhb.cw.invoice.rpc.SysUserApiExp;
import com.hbhb.cw.invoice.service.Invoice3vatService;
import com.hbhb.cw.invoice.web.vo.Invoice3AddVO;
import com.hbhb.cw.invoice.web.vo.Invoice3ResVO;
import com.hbhb.cw.invoice.web.vo.Invoice3VO;
import com.hbhb.cw.invoice.web.vo.Invoice3vatReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceByCondVO;
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
 * 3%增值税专票Service业务层处理
 *
 * @author ruoyi
 * @date 2020-06-02
 */
@Service
@Slf4j
public class Invoice3vatServiceImpl implements Invoice3vatService {

    @Resource
    private Invoice3vatMapper invoice3vatMapper;

    @Resource
    private DictApiExp dictApiExp;

    @Resource
    private SysUserApiExp sysUserApiExp;

    /**
     * 查询3%增值税专票
     *
     * @param vat3Id 3%增值税专票ID
     * @return 3%增值税专票
     */
    @Override
    public Invoice3vat selectInvoice3vatById(Long vat3Id) {
        return invoice3vatMapper.selectByPrimaryKey(vat3Id);
    }

    /**
     * 经过排序查询3导出%增值税专票列表
     *
     * @param ids 3%增值税专票id
     * @return 3%增值税专票
     */
    @Override
    public List<Invoice3VO> selectListByIds(List<Long> ids) {
        List<Invoice3vat> invoice3vats = invoice3vatMapper.selectListByIds(ids);
        List<Invoice3VO> invoice3VOS = BeanConverter.copyBeanList(invoice3vats, Invoice3VO.class);
        // 设置excel每条数据的行号
        for (int i = 0; i < invoice3VOS.size(); i++) {
            invoice3VOS.get(i).setLineNumber(i + 1);
            Date date = invoice3vats.get(i).getInvoiceDate();
            String invoiceDate = DateUtil.formatDate(date, "yyyy-MM-dd");
            String replace = invoiceDate.substring(0, 4) + "/" + invoiceDate.substring(5, 7) + "/"
                    + invoiceDate.substring(8);
            invoice3VOS.get(i).setInvoiceDate(replace);
        }
        return invoice3VOS;
    }

    /**
     * 通过条件查询3%增值税专票列表
     */
    @Override
    public Page<Invoice3ResVO> selectInvoice3vatByCond(Integer pageNum, Integer pageSize,
                                                       Invoice3vatReqVO invoice3vatReqVO) {
        //判断是否为管理员
        boolean adminRole = sysUserApiExp.isAdmin(invoice3vatReqVO.getUserId());
        if (adminRole) {
            invoice3vatReqVO.setUserId(null);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Invoice3ResVO> list = invoice3vatMapper.selectListByCond(invoice3vatReqVO);
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
        int count = invoice3vatMapper.countByCond(invoice3vatReqVO);
        return new Page<>(list, (long) count);
    }

    @Override
    public List<Invoice3VO> listInvoice3vatByCond(Invoice3vatReqVO cond) {
        //判断是否为管理员
        boolean adminRole = sysUserApiExp.isAdmin(cond.getUserId());
        if (adminRole) {
            cond.setUserId(null);
        }
        List<Invoice3ResVO> list = invoice3vatMapper.selectListByCond(cond);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setLineNumber(String.valueOf(i + 1));
        }
        List<Invoice3VO> invoice3VOS = BeanConverter.copyBeanList(list, Invoice3VO.class);
        for (int i = 0; i < invoice3VOS.size(); i++) {
            invoice3VOS.get(i).setLineNumber(i + 1);
            String invoiceDate = invoice3VOS.get(i).getInvoiceDate();
            String replace = invoiceDate.substring(0, 4) + "/" + invoiceDate.substring(5, 7) + "/"
                    + invoiceDate.substring(8);
            invoice3VOS.get(i).setInvoiceDate(replace);
        }
        return invoice3VOS;
    }

    /**
     * 3%发票列表字段转换
     */
    private void convertInvoiceResVO(Invoice3ResVO vo,  List<DictVO> dictListByType,
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
     * 新增3%增值税专票
     *
     * @return 结果
     */
    @Override
    public void insertInvoice3vat(Invoice3AddVO invoice3AddVO, Integer userId) {
        UserInfo user = sysUserApiExp.getUserInfoById(userId);
        Invoice3vat invoice3vat = new Invoice3vat();
        BeanUtils.copyProperties(invoice3AddVO, invoice3vat);
        Date date = DateUtil.string2DateYMD(invoice3AddVO.getInvoiceDate());
        invoice3vat.setInvoiceDate(date);
        //赋值单位名称和导入人员
        invoice3vat.setUserId(user.getId());
        invoice3vat.setUnitId(user.getUnitId());
        invoice3vat.setiTime(new Date());
        invoice3vat.setBuyerTaxId(InvoiceBuyer.BUYER_NUMBER.value());
        invoice3vat.setProjectProperties(InvoiceBuyer.ATTRIBUTES.value());
        invoice3vatMapper.insert(invoice3vat);
    }

    /**
     * 修改3%增值税专票
     *
     * @param invoice3vat 3%增值税专票
     * @return 结果
     */
    @Override
    public int updateInvoice3vat(Invoice3vat invoice3vat) {
        return invoice3vatMapper.updateByPrimaryKey(invoice3vat);
    }


    /**
     * 删除3%增值税专票对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInvoice3vatByIds(List<String> ids) {
        return invoice3vatMapper.deleteBatchByIds(ids);
    }

    /**
     * 删除3%增值税专票信息
     *
     * @param vat3Id 3%增值税专票ID
     * @return 结果
     */
    @Override
    public int deleteInvoice3vatById(Long vat3Id) {
        return invoice3vatMapper.deleteByPrimaryKey(vat3Id);
    }

    /**
     * 通过发票号码查询
     */
    @Override
    public Invoice3vat selectInvoice3vatByNumber(Invoice3AddVO invoice3AddVO) {
        InvoiceByCondVO invoiceByCondVO = new InvoiceByCondVO();
        BeanUtils.copyProperties(invoice3AddVO, invoiceByCondVO);
        return invoice3vatMapper.selectByNumber(invoiceByCondVO);
    }

    @Override
    public Invoice3vat selectById(Long vat3Id) {
        return invoice3vatMapper.selectById(vat3Id);
    }
}
