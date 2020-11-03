package org.men.decorator;

import java.math.BigDecimal;

/**
 * @ClassName CouponDecorator
 * @Description 计算使用优惠后的金额
 * @Author
 * @Date 2020/11/3 10:21
 * @Version V1.0.0
 **/
public class CouponDecorator extends BaseCountDecorator{

    public CouponDecorator(IBaseCount count) {
        super(count);
    }

    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        super.countPayMoney(orderDetail);
        payTotalMoney = countCouponPayMoney(orderDetail);
        return payTotalMoney;
    }

    private BigDecimal countCouponPayMoney(OrderDetail orderDetail) {

        BigDecimal coupon =  orderDetail.getMerchandise().getSupportPromotions().get(PromotionType.COUPON).getUserCoupon().getCoupon();
        System.out.println("优惠券金额：" + coupon);

        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(coupon));
        return orderDetail.getPayMoney();
    }
}