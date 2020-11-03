package org.men.decorator;

import lombok.Data;

/**
 * @ClassName SupportPromotions
 * @Description 促销类型
 * @Author
 * @Date 2020/11/3 10:15
 * @Version V1.0.0
 **/
@Data
public class SupportPromotions  implements Cloneable{
    private int id;//该商品促销的ID
    private PromotionType promotionType;//促销类型 1\优惠券 2\红包
    private int priority; //优先级
    private UserCoupon userCoupon; //用户领取该商品的优惠券
    private UserRedPacket userRedPacket; //用户领取该商品的红包

    //重写clone方法
    public SupportPromotions clone(){
        SupportPromotions supportPromotions = null;
        try{
            supportPromotions = (SupportPromotions)super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return supportPromotions;
    }
}
