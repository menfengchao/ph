package org.men.mmodulearticle.core.enums;

/**
 * @ClassName ArticleTypeEnum
 * @Description 文章类型枚举
 * @Author SuperMen
 * Date 2019/9/26 9:32
 * @Version 1.0
 **/
public enum ArticleTypeEnum {

    /**
     * 纯文字/文章
     */
    ART10010001(10010001, "普通文章"),

    /**
     * .md / 文章
     */
    ART1001002(10010002, "md文章"),

    /**
     * 包含图片
     */
    ART10010003(10010003, "图片文章");


    ArticleTypeEnum(Integer number,String name){
        this.number = number;
        this.name = name;
    }

    private  Integer number;

    private  String  name;

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
