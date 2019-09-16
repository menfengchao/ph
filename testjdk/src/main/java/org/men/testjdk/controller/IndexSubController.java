package org.men.testjdk.controller;

import org.men.testjdk.service.IndexSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexSubController
 * @Description 测试Spring 发布订阅嵌入(观察者模式)
 * @Author SuperMen
 * Date 2019/9/9 10:43
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/indexSub")
public class IndexSubController {

    @Autowired
    IndexSubService  indexSubService;

    @RequestMapping("/publishSubscription")
    public void publishSubscription(){
        indexSubService.publishSubscription();
    }

}
