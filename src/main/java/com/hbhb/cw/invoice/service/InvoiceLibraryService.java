package com.hbhb.cw.invoice.service;

import com.hbhb.cw.invoice.web.vo.InvoiceGoodsImportVO;
import com.hbhb.cw.invoice.web.vo.InvoiceLibraryImportVO;

import java.util.List;

/**
 * @author yzc
 * @since 2020-09-17
 */
public interface InvoiceLibraryService {

    void saveInvoiceLibrary(List<InvoiceLibraryImportVO> dataList);

    void saveInvoiceGoods(List<InvoiceGoodsImportVO> dataList);

    String getTime();
}
