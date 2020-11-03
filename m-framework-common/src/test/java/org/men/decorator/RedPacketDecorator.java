package org.men.decorator;

import java.math.BigDecimal;

/**
 * @ClassName RedPacketDecorator
 * @Description 计算使用红包后的金额
 * @Author
 * @Date 2020/11/3 10:22
 * @Version V1.0.0
 **/
public class RedPacketDecorator extends BaseCountDecorator{

    public RedPacketDecorator(IBaseCount count) {
        super(count);
    }

    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        super.countPayMoney(orderDetail);
        payTotalMoney = countCouponPayMoney(orderDetail);
        return payTotalMoney;
    }

    private BigDecimal countCouponPayMoney(OrderDetail orderDetail) {

        BigDecimal redPacket = orderDetail.getMerchandise().getSupportPromotions().get(PromotionType.REDPACKED).getUserRedPacket().getRedPacket();
        System.out.println("红包优惠金额：" + redPacket);

        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(redPacket));
        return orderDetail.getPayMoney();
    }
}