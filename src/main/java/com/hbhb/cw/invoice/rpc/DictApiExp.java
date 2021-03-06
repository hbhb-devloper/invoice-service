package com.hbhb.cw.invoice.rpc;

import com.hbhb.cw.systemcenter.api.DictApi;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yzc
 * @since 2020-11-27
 */
@FeignClient(value = "${provider.system-center}", url = "${feign-url}", contextId = "SysDictApi", path = "dict")
public interface DictApiExp extends DictApi {
}
