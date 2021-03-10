package org.men.singleton;

import io.swagger.models.auth.In;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.Map;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/3/8 9:03 下午
 **/
public class BeanTest implements BeanDefinitionRegistryPostProcessor {

    public Integer getOne() {
        return one;
    }

    public void setOne(Integer one) {
        this.one = one;
    }

    private Integer one;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(BeanTest.class);
        beanDefinitionRegistry.registerBeanDefinition("beanTest", rootBeanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<String, Object> beanMap = configurableListableBeanFactory.getBeansWithAnnotation(Autowired.class);
    }
}
