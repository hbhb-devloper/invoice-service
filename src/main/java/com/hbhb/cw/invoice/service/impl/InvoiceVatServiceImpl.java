package com.hbhb.cw.invoice.service.impl;

import com.github.pagehelper.PageHelper;
import com.hbhb.core.bean.BeanConverter;
import com.hbhb.core.utils.DateUtil;
import com.hbhb.cw.invoice.common.DictType;
import com.hbhb.cw.invoice.mapper.InvoiceVatMapper;
import com.hbhb.cw.invoice.model.InvoiceVat;
import com.hbhb.cw.invoice.rpc.DictApiExp;
import com.hbhb.cw.invoice.rpc.SysUserApiExp;
import com.hbhb.cw.invoice.rpc.UnitApiExp;
import com.hbhb.cw.invoice.service.InvoiceVatService;
import com.hbhb.cw.invoice.web.vo.InvoiceByCondVO;
import com.hbhb.cw.invoice.web.vo.InvoiceVatAddVO;
import com.hbhb.cw.invoice.web.vo.InvoiceVatReqVO;
import com.hbhb.cw.invoice.web.vo.InvoiceVatResVO;
import com.hbhb.cw.invoice.web.vo.InvoiceVatVO;
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
 * 增值税普票Service业务层处理
 *
 * @author ruoyi
 * @date 2020-06-01
 */
@Service
@Slf4j
public class InvoiceVatServiceImpl implements InvoiceVatService {

    @Value("${cw.invoice.invoice.buyer-tax-id}")
    private String buyerTaxId;

    @Resource
    private InvoiceVatMapper invoiceVatMapper;

    @Resource
    private DictApiExp dictApiExp;

    @Resource
    private SysUserApiExp sysUserApiExp;

    @Resource
    private UnitApiExp unitApiExp;

    /**
     * 查询增值税普票
     *
     * @param vatId 增值税普票ID
     * @return 增值税普票
     */
    @Override
    public InvoiceVat selectInvoiceVatById(Long vatId) {
        return invoiceVatMapper.selectByPrimaryKey(vatId);
    }

    /**
     * 导出增值税普票列表
     *
     * @param ids 增值税普票
     * @return 增值税普票
     */
    @Override
    public List<InvoiceVatVO> selectListByIds(List<Long> ids) {
        List<InvoiceVat> invoiceVats = invoiceVatMapper.selectListByIds(ids);
        List<InvoiceVatVO> invoiceVatVOS = BeanConverter
                .copyBeanList(invoiceVats, InvoiceVatVO.class);
        // 设置excel每条数据的行号
        for (int i = 0; i < invoiceVatVOS.size(); i++) {
            invoiceVatVOS.get(i).setLineNumber(i + 1);
            Date date = invoiceVats.get(i).getInvoiceDate();
            String invoiceDate = DateUtil.formatDate(date, "yyyy-MM-dd");
            String replace = invoiceDate.substring(0, 4) + "/" + invoiceDate.substring(5, 7) + "/"
                    + invoiceDate.substring(8);
            invoiceVatVOS.get(i).setInvoiceDate(replace);
        }

        return invoiceVatVOS;
    }


    /**
     * 通过条件查询
     */
    @Override
    public Page<InvoiceVatResVO> selectInvoiceVatByCond(Integer pageNum, Integer pageSize,
            InvoiceVatReqVO invoiceVatReqVO) {
        //判断是否为管理员
        boolean adminRole = sysUserApiExp.isAdmin(invoiceVatReqVO.getUserId());
        if (adminRole) {
            invoiceVatReqVO.setUserId(null);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<InvoiceVatResVO> list = invoiceVatMapper.selectListByCond(invoiceVatReqVO);
         List<DictVO> dictListByType = dictApiExp
                .getDict(DictType.INVOICE.value(), DictCode.INVOICE_TYPE_COMMON.value());
        list.forEach(vo -> convertInvoiceVO(vo, dictListByType));
        for (int i = 0; i < list.size(); i++) {
            int h = (pageNum - 1) * pageSize;
            list.get(i).setLineNumber(String.valueOf(h + i + 1));
        }
        int count = invoiceVatMapper.countByCond(invoiceVatReqVO);
        return new Page<>(list, (long) count);
    }

    @Override
    public List<InvoiceVatVO> listInvoiceVatByCond(InvoiceVatReqVO invoiceVatReqVO) {
        //判断是否为管理员
        boolean adminRole = sysUserApiExp.isAdmin(invoiceVatReqVO.getUserId());
        if (adminRole) {
            invoiceVatReqVO.setUserId(null);
        }
        List<InvoiceVatResVO> list = invoiceVatMapper.selectListByCond(invoiceVatReqVO);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setLineNumber(String.valueOf(i + 1));
        }
        List<InvoiceVatVO> invoiceVatVOS = BeanConverter
                .copyBeanList(list, InvoiceVatVO.class);
        for (int i = 0; i < invoiceVatVOS.size(); i++) {
            invoiceVatVOS.get(i).setLineNumber(i + 1);
            String invoiceDate = invoiceVatVOS.get(i).getInvoiceDate();
            String replace = invoiceDate.substring(0, 4) + "/" + invoiceDate.substring(5, 7) + "/"
                    + invoiceDate.substring(8);
            invoiceVatVOS.get(i).setInvoiceDate(replace);
        }
        return invoiceVatVOS;
    }

    /**
     * 普票列表字段转换
     */
    private void convertInvoiceVO(InvoiceVatResVO vo,  List<DictVO> dictListByType) {
        for (DictVO sysDict : dictListByType) {
            if (sysDict.getValue().equals(vo.getInvoiceType())) {
                vo.setInvoiceType(sysDict.getLabel());
            }
        }
    }

    /**
     * 新增增值税普票
     *
     * @return 结果
     */
    @Override
    public void insertInvoiceVat(InvoiceVatAddVO invoiceVatAddVO, Integer userId) {
        UserInfo user = sysUserApiExp.getUserInfoById(userId);
        InvoiceVat invoiceVat = new InvoiceVat();
        BeanUtils.copyProperties(invoiceVatAddVO, invoiceVat);
        Date date = DateUtil.string2DateYMD(invoiceVatAddVO.getInvoiceDate());
        invoiceVat.setInvoiceDate(date);
        //赋值单位名称和导入人员
        invoiceVat.setUserId(user.getId());
        invoiceVat.setUnitId(user.getUnitId());
        invoiceVat.setiTime(new Date());
        String checkCode = invoiceVat.getCheckCode();
        invoiceVat.setCheckCode(checkCode.substring(checkCode.length() - 6));
        invoiceVat.setBuyerTaxId(buyerTaxId);
        invoiceVatMapper.insert(invoiceVat);
    }

    /**
     * 修改增值税普票
     *
     * @param invoiceVat 增值税普票
     * @return 结果
     */
    @Override
    public int updateInvoiceVat(InvoiceVat invoiceVat) {
        return invoiceVatMapper.updateByPrimaryKey(invoiceVat);
    }

    /**
     * 删除增值税普票对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInvoiceVatByIds(List<String> ids) {

        return invoiceVatMapper.deleteBatchByIds(ids);
    }

    /**
     * 删除增值税普票信息
     *
     * @param vatId 增值税普票ID
     * @return 结果
     */
    @Override
    public int deleteInvoiceVatById(Long vatId) {
        return invoiceVatMapper.deleteByPrimaryKey(vatId);
    }

    @Override
    public InvoiceVat selectById(Long id) {
        return invoiceVatMapper.selectById(id);
    }


    /**
     * 通过发票号码查找发票
     */
    @Override
    public InvoiceVat selectInvoiceVatByNumber(InvoiceVatAddVO invoiceVatAddVO) {
        InvoiceByCondVO invoiceByCondVO = new InvoiceByCondVO();
        BeanUtils.copyProperties(invoiceVatAddVO, invoiceByCondVO);
        return invoiceVatMapper.selectByNumber(invoiceByCondVO);
    }
}
