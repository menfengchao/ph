package org.men.decorator;

import java.math.BigDecimal;

/**
 * @ClassName BaseCountDecorator
 * @Description 计算支付金额的抽象类
 * @Author
 * @Date 2020/11/3 10:20
 * @Version V1.0.0
 **/
public class BaseCountDecorator implements IBaseCount{

    private IBaseCount count;

    public BaseCountDecorator(IBaseCount count) {
        this.count = count;
    }

    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        if(count!=null) {
            payTotalMoney = count.countPayMoney(orderDetail);
        }
        return payTotalMoney;
    }
}