package org.men.mmodulearticle.core.controllerapi.controller;

import org.men.frameworkcommon.modelEntity.user.UserDto;
import org.men.mmodulearticle.core.controllerapi.ArticleController;
import org.men.mmodulearticle.core.entity.Article;
import org.men.mmodulearticle.core.service.ArticleService;
import org.men.mmodulearticle.core.service.serviceclient.CallUserInterfaceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName ArticleControllerimpl
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/26 9:28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/server/article")
public class ArticleControllerimpl  implements ArticleController {


    @Autowired
    ArticleService articleService;

    @Resource
    private CallUserInterfaceClient callUserInterfaceClient;

    @Override
    @PostMapping("/save")
    public String save(@RequestBody Article articleVo) {
        return articleService.save(articleVo);
    }

    @Override
    @GetMapping("/hellow")
    public UserDto hellow(@RequestParam("id") String id) {
        UserDto byId = callUserInterfaceClient.findById(id);
        return byId;
    }
}
