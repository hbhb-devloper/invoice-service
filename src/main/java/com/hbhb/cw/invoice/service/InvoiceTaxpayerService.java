package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.model.InvoiceTaxpayerCredentials;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerShowVO;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerVO;
import com.hbhb.springboot.web.view.Page;

import java.util.List;

/**
 * @author yzc
 * @since 2020-10-20
 */
public interface InvoiceTaxpayerService {

    /**
     * 按条件查询纳税资质人（分页）
     */
    Page<InvoiceTaxpayerShowVO> getPageByCont(InvoiceTaxpayerVO cond, Integer pageNum, Integer pageSize);

    /**
     * 批量导入保存纳税资质人
     */
    void saveInvoiceTaxpayer(List<InvoiceTaxpayerImportVO> dataList, Integer unitId);

    /**
     * 得到纳税人资质
     */
    List<InvoiceTaxpayerCredentials> getListByCont(Integer unitId, String channerNum);

    List<String> getMsg();
}
