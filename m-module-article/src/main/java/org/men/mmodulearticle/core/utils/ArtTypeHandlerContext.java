package org.men.mmodulearticle.core.utils;

import org.men.mmodulearticle.core.utils.handler.ArtAbstractHandler;

import java.util.Map;

/**
 * @ClassName ArtTypeHandlerContext
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/26 9:48
 * @Version 1.0
 **/
public class ArtTypeHandlerContext {

    private Map<Integer ,Class> handlerMap;

    public ArtTypeHandlerContext(Map<Integer, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public ArtAbstractHandler getInstance(Integer type){
        Class aClass = handlerMap.get(type);
        if(aClass ==null)
            throw new IllegalArgumentException("not found handler fot type:"+type);
        return (ArtAbstractHandler)BeanTools.getBaean(aClass);
    }

}
