package org.men.decorator;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName UserCoupon
 * @Description
 * @Author
 * @Date 2020/11/3 10:16
 * @Version V1.0.0
 **/
@Data
public class UserCoupon {
    private int id; //优惠券ID
    private int userId; //领取优惠券用户ID
    private String sku; //商品SKU
    private BigDecimal coupon; //优惠金额
}
