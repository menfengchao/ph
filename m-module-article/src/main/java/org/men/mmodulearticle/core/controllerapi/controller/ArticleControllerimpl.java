package org.men.mmodulearticle.core.controllerapi.controller;

import org.men.mmodulearticle.core.controllerapi.ArticleController;
import org.men.mmodulearticle.core.entity.Article;
import org.men.mmodulearticle.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    @PostMapping("/save")
    public String save(@RequestBody Article articleVo) {
        return articleService.save(articleVo);
    }
}
