package org.men.decorator;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName PromotionFactory
 * @Description 计算促销后的价格
 * @Author
 * @Date 2020/11/3 10:23
 * @Version V1.0.0
 **/
public class PromotionFactory{

    public static BigDecimal getPayMoney(OrderDetail orderDetail) {

        //获取给商品设定的促销类型
        Map<PromotionType, SupportPromotions> supportPromotionslist = orderDetail.getMerchandise().getSupportPromotions();

        //初始化计算类
        IBaseCount baseCount = new BaseCount();
        if(supportPromotionslist!=null && supportPromotionslist.size()>0) {
            for(PromotionType promotionType: supportPromotionslist.keySet()) {//遍历设置的促销类型，通过装饰器组合促销类型
                baseCount = protmotion(supportPromotionslist.get(promotionType), baseCount);
            }
        }
        return baseCount.countPayMoney(orderDetail);
    }

    /**
     * 组合促销类型
     * @param supportPromotions
     * @param baseCount
     * @return
     */
    private static IBaseCount protmotion(SupportPromotions supportPromotions, IBaseCount baseCount) {
        if(supportPromotions.getPromotionType()==PromotionType.COUPON) {
            baseCount = new CouponDecorator(baseCount);
        }else if(supportPromotions.getPromotionType()==PromotionType.REDPACKED) {
            baseCount = new RedPacketDecorator(baseCount);
        }
        return baseCount;
    }



}