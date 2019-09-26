package org.men.mmodulearticle.core.utils.handler;

import org.men.mmodulearticle.core.enums.ArticleTypeEnum;

import java.lang.annotation.*;

/**
 * @ClassName ArtType
 * @Description 自定义注解
 * @Author SuperMen
 * Date 2019/9/26 10:48
 * @Version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ArtType {
    ArticleTypeEnum value();
}
