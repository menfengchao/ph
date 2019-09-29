package org.men.mmodulearticle.core.controllerapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.men.frameworkcommon.modelEntity.user.UserDto;
import org.men.mmodulearticle.core.entity.Article;

/**
 * @ClassName ArticleController
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/26 9:22
 * @Version 1.0
 **/
@Api(value = "文章控制器", tags = "文章控制器")
public interface ArticleController {

    @ApiOperation(value = "文章新增接口", notes = "文章新增接口")
    String save(Article articleVo);

    UserDto hellow(String id);
}
