package org.men.mmodulearticle.core.utils.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.men.mmodulearticle.core.entity.Article;
import org.men.mmodulearticle.core.enums.ArticleTypeEnum;
import org.men.mmodulearticle.core.utils.handler.ArtAbstractHandler;
import org.men.mmodulearticle.core.utils.handler.ArtType;
import org.springframework.stereotype.Component;

/**
 * @ClassName ArtAbstractTextHander
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/26 10:41
 * @Version 1.0
 **/

@Slf4j
@Component
@ArtType(value = ArticleTypeEnum.ART10010001 )
public class ArtAbstractTextHandler implements ArtAbstractHandler {

    @Override
    public String handle(Article article) {
        log.info("ArtAbstractTextHandler:"+article.toString());
        return "ArtAbstractTextHandler";
    }
}
