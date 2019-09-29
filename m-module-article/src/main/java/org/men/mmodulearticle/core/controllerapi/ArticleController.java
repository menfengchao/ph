package org.men.mmodulearticle.core.controllerapi;

import org.men.frameworkcommon.modelEntity.user.UserDto;
import org.men.mmodulearticle.core.entity.Article;

/**
 * @ClassName ArticleController
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/26 9:22
 * @Version 1.0
 **/
public interface ArticleController {

    String save(Article articleVo);

    UserDto hellow( String id);
}
