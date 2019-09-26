package org.men.mmodulearticle.core.service;

import org.men.mmodulearticle.core.entity.Article;
import org.men.mmodulearticle.core.utils.ArtTypeHandlerContext;
import org.men.mmodulearticle.core.utils.handler.ArtAbstractHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/26 9:26
 * @Version 1.0
 **/
@Service
public class ArticleService {

    @Resource
    ArtTypeHandlerContext artTypeHandlerContext;

    public String save( Article articleVo) {
        ArtAbstractHandler instance = artTypeHandlerContext.getInstance(articleVo.getType());
        String handle = instance.handle(articleVo);
        return handle;
    }

}
