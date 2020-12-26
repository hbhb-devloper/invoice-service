package com.hbhb.cw.invoice.common.exception;

import com.hbhb.core.bean.MessageConvert;
import com.hbhb.cw.invoice.common.config.InvoiceErrorCode;
import com.hbhb.web.exception.BusinessException;

import lombok.Getter;

/**
 * @author wangxiaogang
 */
@Getter
public class InvoiceException extends BusinessException {
    private static final long serialVersionUID = 8071162749850615442L;

    private final String code;

    public InvoiceException(InvoiceErrorCode errorCode) {
        super(errorCode.getCode(), MessageConvert.convert(errorCode.getMessage()));
        this.code = errorCode.getCode();
    }
}
