package org.men.mmodulearticle.core.utils.handler;

import com.google.common.collect.Maps;
import org.men.mmodulearticle.core.enums.ArticleTypeEnum;
import org.men.mmodulearticle.core.utils.ArtTypeHandlerContext;
import org.men.mmodulearticle.core.utils.ClassScaner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName ArthandlerProcessor
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/26 10:51
 * @Version 1.0
 **/
@Component
public class ArthandlerProcessor implements BeanFactoryPostProcessor {

    private static final String HANFDLER_PACKAGE =  "org.men.mmodulearticle.core.utils.handler.impl";

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<Integer,Class> handlerMap = Maps.newHashMapWithExpectedSize(3);
        ClassScaner.scan(HANFDLER_PACKAGE,ArtType.class).forEach(clazz->{
            ArticleTypeEnum value = clazz.getAnnotation(ArtType.class).value();
            handlerMap.put(value.getNumber(),clazz);
        });
        ArtTypeHandlerContext context = new ArtTypeHandlerContext(handlerMap);
        configurableListableBeanFactory.registerSingleton(ArtTypeHandlerContext.class.getName(),context);
    }
}
