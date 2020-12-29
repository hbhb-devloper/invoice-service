package com.hbhb.cw.invoice.rpc;

import com.hbhb.cw.systemcenter.api.UnitApi;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yzc
 * @since 2020-11-27
 */
@FeignClient(value = "${provider.system-center}", url = "${feign-url}", contextId = "UnitApi", path = "unit")
public interface UnitApiExp extends UnitApi {
}
