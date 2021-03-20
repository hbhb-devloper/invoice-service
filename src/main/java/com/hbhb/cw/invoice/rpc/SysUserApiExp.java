package com.hbhb.cw.invoice.rpc;

import com.hbhb.cw.systemcenter.api.UserApi;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${provider.system-center}", url = "${feign-url}", contextId = "SysUserApi", path = "user")
public interface SysUserApiExp extends UserApi {

}
