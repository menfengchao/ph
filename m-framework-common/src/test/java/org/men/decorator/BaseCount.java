package org.men.decorator;

import java.math.BigDecimal;

/**
 * @ClassName BaseCount
 * @Description 基本支付类
 * @Author
 * @Date 2020/11/3 10:18
 * @Version V1.0.0
 **/
public class BaseCount implements IBaseCount{

    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        orderDetail.setPayMoney(orderDetail.getMerchandise().getPrice());
        System.out.println("商品原单价金额为：" +  orderDetail.getPayMoney());
        return orderDetail.getPayMoney();
    }

}