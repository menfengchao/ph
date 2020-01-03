package org.mern.servereureka;

/**
 * @ClassName ColnoObject
 * @Description TODO
 * @Author SuperMen
 * Date 2019/10/14 11:01
 * @Version 1.0
 **/
public class ColnoObject implements Cloneable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public ColnoObject clone(){
        ColnoObject colnoObject = null;
        try {
            colnoObject = (ColnoObject)super.clone();;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  colnoObject;
    }
}
