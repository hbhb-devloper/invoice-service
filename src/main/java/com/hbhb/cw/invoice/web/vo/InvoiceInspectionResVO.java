package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-11-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceInspectionResVO implements Serializable {

    private static final long serialVersionUID = 8373596717368721760L;

    private List<InvoiceInspectionVO> list;

    private Integer total;
}
