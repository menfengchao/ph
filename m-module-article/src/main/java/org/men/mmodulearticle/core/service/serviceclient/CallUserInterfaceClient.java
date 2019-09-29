package org.men.mmodulearticle.core.service.serviceclient;

import org.men.frameworkcommon.modelEntity.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName CallUserInterfaceClient
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/29 15:21
 * @Version 1.0
 **/
@FeignClient(name = "MMODULEUSER")
public interface CallUserInterfaceClient {

    @GetMapping(value = "/usermodule/server/user/findById")
    UserDto findById(@RequestParam("id") String id);
}
