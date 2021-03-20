package com.hbhb.cw.invoice.web.vo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzc
 * @since 2020-11-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceContrastResVO implements Serializable {

    private static final long serialVersionUID = 8702006999675984828L;

    private List<InvoiceContrastVO> list;

    private Integer total;
}
