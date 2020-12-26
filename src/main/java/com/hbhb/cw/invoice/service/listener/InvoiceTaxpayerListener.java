package com.hbhb.cw.invoice.service.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.cw.invoice.common.exception.InvoiceException;
import com.hbhb.cw.invoice.service.InvoiceTaxpayerService;
import com.hbhb.cw.invoice.web.vo.InvoiceTaxpayerImportVO;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yzc
 * @since 2020-10-20
 */
@Slf4j
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class InvoiceTaxpayerListener extends AnalysisEventListener {
    /**
     * 批处理条数，每隔多少条清理一次list ，方便内存回收
     */
    private static final int BATCH_COUNT = 10000;
    /**
     * 数据行
     */
    private final List<InvoiceTaxpayerImportVO> dataList = new ArrayList<>();

    private final InvoiceTaxpayerService invoiceTaxpayerService;
    private final AtomicInteger unitId;

    public InvoiceTaxpayerListener(InvoiceTaxpayerService invoiceTaxpayerService, AtomicInteger unitId) {
        this.invoiceTaxpayerService = invoiceTaxpayerService;
        this.unitId = unitId;
    }

    /**
     * 每次读取一条数据时调用该方法
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        dataList.add((InvoiceTaxpayerImportVO) object);
        if (dataList.size() >= BATCH_COUNT) {
            saveData();
            dataList.clear();
        }
    }

    /**
     * 所有数据解析完成后调用该方法
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 确保最后一次的数据入库
        if (dataList.isEmpty()){
            throw new InvoiceException(InvoiceErrorCode.INVOICE_DATA_IMPORT_ERROR);
        }
        saveData();
        dataList.clear();
    }

    /**
     * 获取表头
     */
    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {
    }

    /**
     * 保存预算数据
     */
    private void saveData() {
        if (!CollectionUtils.isEmpty(dataList)) {
            invoiceTaxpayerService.saveInvoiceTaxpayer(dataList, unitId.get());
        }
    }
}
